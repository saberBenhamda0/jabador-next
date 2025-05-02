package com.jabadoor.auth_service.controller;

import com.jabadoor.auth_service.dto.LoginDTO;
import com.jabadoor.auth_service.dto.SignUpDTO;
import com.jabadoor.auth_service.service.AuthService;
import com.jabadoor.auth_service.service.JwtService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final JwtService jwtService;
    private final AuthService authService;

    public AuthController(JwtService jwtService, AuthService authService) {
        this.jwtService = jwtService;
        this.authService = authService;
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody LoginDTO loginDTO){
    return authService.login(loginDTO.username(), loginDTO.password());
    }

    @PostMapping("/signup")
    public Map<String, String> login(@RequestBody SignUpDTO signUpDTO){
        return authService.signup(signUpDTO);
    }
}
