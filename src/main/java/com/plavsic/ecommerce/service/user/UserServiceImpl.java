package com.plavsic.ecommerce.service.user;

import com.plavsic.ecommerce.dto.user.UserDTO;
import com.plavsic.ecommerce.model.user.Role;
import com.plavsic.ecommerce.model.user.User;
import com.plavsic.ecommerce.generic.repository.GenericRepository;
import com.plavsic.ecommerce.generic.service.AbstractService;
import com.plavsic.ecommerce.repository.user.RoleRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;

@Service
public class UserServiceImpl extends AbstractService<UserDTO,User> {

    private final RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(ModelMapper modelMapper,
                           GenericRepository<User> repository,
                           RoleRepository roleRepository) {
        super(modelMapper, repository,UserDTO.class,User.class);
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional
    public UserDTO save(UserDTO userDTO) {
        userDTO.setId(null);
        Role role = roleRepository.findByName("ROLE_USER").orElse(null);
        userDTO.getRoles().add(role);
        User user = modelMapper.map(userDTO,User.class);
        user = repository.save(user);
        return modelMapper.map(user,UserDTO.class);
    }
}
