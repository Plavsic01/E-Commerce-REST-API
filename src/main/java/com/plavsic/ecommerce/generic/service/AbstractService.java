package com.plavsic.ecommerce.generic.service;

import com.plavsic.ecommerce.generic.repository.GenericRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @param <T> - Type for DTO (e.g. UserDTO)
 * @param <U> - Type for Entity (e.g. User)
 */

@Service
public abstract class AbstractService<T,U> implements GenericService<T>{

    private final ModelMapper modelMapper;
    private final GenericRepository<U> repository;
    private final Class<U> type;
    private final Class<T> typeDTO;

    public AbstractService(ModelMapper modelMapper, GenericRepository<U> repository,Class<T> typeDTO, Class<U> type) {
        this.modelMapper = modelMapper;
        this.repository = repository;
        this.type = type;
        this.typeDTO = typeDTO;
    }


    @Override
    public T findById(Long id) {
        U obj = repository.findById(id).orElse(null);
        if(obj == null) {
            return null;
        }
        return modelMapper.map(obj, typeDTO);
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

    @Override
    public T update(Long id, T t) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
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
    public boolean delete(Long id) {
        if(repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
