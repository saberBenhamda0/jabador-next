package com.jabadoor.api_gateway.exception.handler;

import jakarta.ws.rs.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class BadRequestExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<Map<String, String>> handleException(BadRequestException exception){
        Map<String, String> response = new HashMap<>();

        response.put("message", exception.getMessage());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
