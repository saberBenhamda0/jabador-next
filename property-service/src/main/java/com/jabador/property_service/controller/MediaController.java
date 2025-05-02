package com.jabador.property_service.controller;

import com.jabador.property_service.service.PropertyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class MediaController {

    private final PropertyService propertyService;

    public MediaController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @GetMapping("/uploads/property/{name}")
    public ResponseEntity<byte[]> readImage(@PathVariable String name) throws IOException {
        return propertyService.readImage("uploads/property/" + name);
    }
}
