package com.plavsic.ecommerce.generic.service;

import com.plavsic.ecommerce.dto.auth.LoginDTO;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    String login(LoginDTO login);
}
