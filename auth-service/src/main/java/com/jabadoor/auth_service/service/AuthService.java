package com.jabadoor.auth_service.service;

import com.jabadoor.auth_service.dto.SignUpDTO;
import com.jabadoor.auth_service.entity.User;
import com.jabadoor.auth_service.exception.UserAlreadyExistException;
import com.jabadoor.auth_service.repository.UserRepository;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, JwtService jwtService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    public Map<String, String> login(String email, String password){
        Optional<User> optionalUser = userRepository.findByEmail(email);
        User user = optionalUser.orElseThrow(() -> new UsernameNotFoundException("there is no user with this email"));

        // comparing the raw passowrd with hash password
        if(!passwordEncoder.matches(password, user.getPassword())){
            throw new BadCredentialsException("the passord  is incorrect");
        }

        Map<String, String> response = new HashMap<>();
        Map<String, String> claims = new HashMap<>();

        claims.put("role", user.getRole());
        claims.put("id", user.getId().toString());
        claims.put("first_name", user.getFirstName());
        claims.put("second_name", user.getSecondName());
        claims.put("email", user.getEmail());
        claims.put("phone_number", user.getPhoneNumber());

        response.put("access", jwtService.generateAccessToken(claims, user.getEmail()));
        response.put("refresh", jwtService.generateRefreshToken(claims, user.getEmail()));

        return response;
    }

    public Map<String, String> signup(SignUpDTO signUpDTO){

        User user = User.builder()
                .firstName(signUpDTO.firstName())
                .secondName(signUpDTO.secondName())
                .email(signUpDTO.email())
                .phoneNumber(signUpDTO.phoneNumber())
                .password(passwordEncoder.encode(signUpDTO.password()))
                .build();


        Optional<User> optionalUser = userRepository.findByEmail(signUpDTO.email());
        if(optionalUser.isPresent()){
            throw new UserAlreadyExistException("there already a user with this email");
        }
        userRepository.save(user);

        Map<String, String> response = new HashMap<>();
        Map<String, String> claims = new HashMap<>();

        claims.put("role", user.getRole());
        claims.put("id", user.getId().toString());
        claims.put("first_name", user.getFirstName());
        claims.put("second_name", user.getSecondName());
        claims.put("email", user.getEmail());
        claims.put("phone_number", user.getPhoneNumber());

        response.put("access", jwtService.generateAccessToken(claims, user.getEmail()));
        response.put("refresh", jwtService.generateRefreshToken(claims, user.getEmail()));

        return response;
    }


}
