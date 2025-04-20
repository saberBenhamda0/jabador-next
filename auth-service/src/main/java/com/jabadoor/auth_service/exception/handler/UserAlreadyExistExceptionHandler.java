package com.jabadoor.auth_service.exception.handler;

import com.jabadoor.auth_service.exception.UserAlreadyExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class UserAlreadyExistExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<Map<String, String>> handleException(UserAlreadyExistException exception){
        Map<String, String> response = new HashMap<>();
        response.put("message", exception.getMessage());

        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);

    }
}
