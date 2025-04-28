package com.jabador.property_service.controller;

import com.jabador.property_service.dto.AmenitieDTO;
import com.jabador.property_service.service.AmenityService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/property/amenity")
public class AmenityController {

    private final AmenityService amenityService;

    public AmenityController(AmenityService amenityService) {
        this.amenityService = amenityService;
    }

    @PostMapping("/save")
    public Map<String, String> saveAmenity(@RequestBody AmenitieDTO amenitieDTO){
        return amenityService.saveAmenity(amenitieDTO);
    }


}
