package com.plavsic.ecommerce.service.user;

import com.plavsic.ecommerce.dto.user.UserDTO;
import com.plavsic.ecommerce.model.user.User;
import com.plavsic.ecommerce.generic.repository.GenericRepository;
import com.plavsic.ecommerce.generic.service.AbstractService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends AbstractService<UserDTO,User> {

    @Autowired
    public UserServiceImpl(ModelMapper modelMapper, GenericRepository<User> repository) {
        super(modelMapper, repository,UserDTO.class,User.class);
    }
}
