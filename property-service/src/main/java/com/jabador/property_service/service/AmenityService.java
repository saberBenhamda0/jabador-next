package com.jabador.property_service.service;

import com.jabador.property_service.dto.AmenitieDTO;
import com.jabador.property_service.entity.Amenitie;
import com.jabador.property_service.repository.AmenitieRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AmenityService {

    private final AmenitieRepository amenitieRepository;

    public AmenityService(AmenitieRepository amenitieRepository) {
        this.amenitieRepository = amenitieRepository;
    }

    public Map<String, String> saveAmenity(AmenitieDTO amenitieDTO){
        Amenitie amenitie = Amenitie.builder()
                .name(amenitieDTO.name())
                .type(amenitieDTO.type())
                .icon(amenitieDTO.icon())
                .build();

        amenitieRepository.save(amenitie);

        Map<String, String> response = new HashMap<>();
        response.put("response", "the amenity has been saved with success");
        return response;
    }
}
