package com.plavsic.ecommerce.controller.user;

import com.plavsic.ecommerce.generic.controller.GenericController;
import com.plavsic.ecommerce.dto.user.UserDTO;
import com.plavsic.ecommerce.model.user.User;
import com.plavsic.ecommerce.generic.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController extends GenericController<UserDTO,User> {

    @Autowired
    public UserController(AbstractService<UserDTO, User> service) {
        super(service);
    }
}
