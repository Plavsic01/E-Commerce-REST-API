package com.plavsic.ecommerce.controller.user;

import com.plavsic.ecommerce.generic.controller.GenericController;
import com.plavsic.ecommerce.dto.user.UserDTO;
import com.plavsic.ecommerce.model.user.User;
import com.plavsic.ecommerce.generic.service.AbstractService;
import com.plavsic.ecommerce.security.AuthUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController extends GenericController<UserDTO,User> {

    @Autowired
    public UserController(AbstractService<UserDTO, User> service) {
        super(service);
    }

}
