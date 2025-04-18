package com.jabadoor.user_service.service;

import com.jabadoor.user_service.entity.User;
import com.jabadoor.user_service.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveUser(){
        User user = User.builder()
                .firstName("first")
                .secondName("second")
                .email("test@gmail.com")
                .phoneNumber("0649713346")
                .role("test")
                .password("dfgsdfsdfg")
                .build();

        userRepository.save(user);
    }
}
