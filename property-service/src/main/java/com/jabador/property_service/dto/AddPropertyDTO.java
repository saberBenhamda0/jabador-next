package com.jabador.property_service.dto;

import com.jabador.property_service.entity.Amenitie;
import com.jabador.property_service.entity.Location;
import com.jabador.property_service.entity.Media;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public record AddPropertyDTO(
        String propertyName,
        String propertyType,
        String propertyDescription,
        int price,
        int maxGuests,
        int bedrooms,
        int bathrooms,
        int rooms,
        int kitchens,
        String country,
        String city,
        String street,
        int postalCode,
        double latitude,
        double longitude,
        long[] amenities,
        long owner,
        List<MediaDTO> images
) {}
