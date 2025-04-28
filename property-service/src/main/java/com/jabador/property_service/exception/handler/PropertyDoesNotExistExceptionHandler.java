package com.jabador.property_service.exception.handler;

import com.jabador.property_service.exception.PropertyDoesExistNotException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class PropertyDoesNotExistExceptionHandler {

    @ExceptionHandler(PropertyDoesExistNotException.class)
    public ResponseEntity<Map<String, String>> handler(PropertyDoesExistNotException exception){
        Map<String, String> response = new HashMap<>();
        response.put("response", "there is no property with this id");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

    }
}
