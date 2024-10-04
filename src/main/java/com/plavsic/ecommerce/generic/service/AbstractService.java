package com.plavsic.ecommerce.generic.service;

import com.plavsic.ecommerce.dto.user.UserDTO;
import com.plavsic.ecommerce.generic.repository.GenericRepository;
import com.plavsic.ecommerce.security.AuthUserDetails;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @param <T> - Type for DTO (e.g. UserDTO)
 * @param <U> - Type for Entity (e.g. User)
 */

@Service
public abstract class AbstractService<T,U> implements GenericService<T>{

    protected final ModelMapper modelMapper;
    protected final GenericRepository<U> repository;
    private final Class<U> type;
    private final Class<T> typeDTO;

    public AbstractService(ModelMapper modelMapper, GenericRepository<U> repository,Class<T> typeDTO, Class<U> type) {
        this.modelMapper = modelMapper;
        this.repository = repository;
        this.type = type;
        this.typeDTO = typeDTO;
    }

    private T findById(Long id) {
        U obj = repository.findById(id).orElse(null);
        if(obj == null) {
            return null;
        }
        return modelMapper.map(obj, typeDTO);
    }

    /**
     * AKO KORISTIMO /users ENDPOINT ONDA SE GLEDA DA LI JE id PARAMETAR JEDNAK
     * userDetails.getId() ZASTO STO SMO U UserControlleru I KORISTIMO UserService
     *
     * AKO JE BILO KOJI DRUGI ENDPOINT KOJI IMA KOMPOZICIJU NA User ONDA SE PREKO
     * REFLEKSIJE TRAZI TO POLJE (user) I GLEDA SE DA LI JE user.getId == userDetails.getId()
     * AKO JESTE TO ZNACI DA USER MOZE DA PRISTUPI SVOJIM PODACIMA NPR U ORDERU
     *
     * @param id
     * @param userDetails
     * @return T
     */
    @Override
    public T findById(Long id, AuthUserDetails userDetails) {
        if(userDetails == null) {
            return findById(id);
        }
        if(isAdmin(userDetails)) {
            return findById(id);
        }else{
            T obj = findById(id);
            try {
                Field usrField = typeDTO.getDeclaredField("user");
                usrField.setAccessible(true);
                UserDTO user = (UserDTO) usrField.get(obj);
                if(Objects.equals(user.getId(), userDetails.getId())) {
                    return obj;
                }
            }catch (NoSuchFieldException | IllegalAccessException e) {
                return Objects.equals(userDetails.getId(),id) ? obj : null;
            }
            return null;
        }
    }

    @Override
    public Map<String, Object> findAll(Pageable pageable) {
        List<T> objects = new ArrayList<>();
        Page<U> page = repository.findAll(pageable);

        page.getContent().forEach(obj -> objects.add(modelMapper.map(obj, typeDTO)));
        Map<String, Object> response = new HashMap<>();
        response.put("items",objects);
        response.put("currentPage",page.getNumber());
        response.put("totalPages",page.getTotalPages());
        response.put("totalItems",page.getTotalElements());
        return response;
    }

    @Override
    public T save(T t) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method setIdMethod = t.getClass().getMethod("setId",Long.class);
        setIdMethod.invoke(t, (Object) null);
        U obj = modelMapper.map(t,type);
        obj = repository.save(obj);
        return modelMapper.map(obj, typeDTO);
    }


    private T update(Long id, T t) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        U obj = repository.findById(id).orElse(null);
        if(obj == null) {
            return null;
        }
        Method setIdMethod = t.getClass().getMethod("setId",Long.class);
        setIdMethod.invoke(t, (Object) null);
        modelMapper.map(t, obj);
        obj = repository.save(obj);
        return modelMapper.map(obj, typeDTO);
    }

    @Override
    public T update(Long id, T t, AuthUserDetails userDetails) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        if(userDetails == null) {
            return update(id,t);
        }
        if(isAdmin(userDetails)) {
            return update(id,t);
        }else{
            return Objects.equals(userDetails.getId(), id) ? update(id,t) : null;
        }
    }


    private boolean delete(Long id) {
        if(repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Long id, AuthUserDetails userDetails) {
        if(userDetails == null) {
            return delete(id);
        }
        if(isAdmin(userDetails)) {
            return delete(id);
        }else{
            return Objects.equals(userDetails.getId(), id) && delete(id);
        }
    }

    private boolean isAdmin(AuthUserDetails userDetails) {
        boolean isAdmin = false;
        for(GrantedAuthority grantedAuthority : userDetails.getAuthorities()) {
            if(grantedAuthority.getAuthority().equals("ROLE_ADMIN")) {
                isAdmin = true;
                break;
            }
        }
        return isAdmin;
    }
}
