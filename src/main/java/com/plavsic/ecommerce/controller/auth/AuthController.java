package com.plavsic.ecommerce.controller.auth;

import com.plavsic.ecommerce.dto.auth.AuthResponseDTO;
import com.plavsic.ecommerce.dto.auth.LoginDTO;
import com.plavsic.ecommerce.generic.service.AuthService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDTO login){
        String token = authService.login(login);
        AuthResponseDTO authResponse = new AuthResponseDTO();
        authResponse.setAccessToken(token);
        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }


    @PostMapping("/clear-session")
    public ResponseEntity<String> clearSession(HttpSession session){
        session.invalidate();
        return new ResponseEntity<>("Session Wiped!", HttpStatus.OK);
    }


}
