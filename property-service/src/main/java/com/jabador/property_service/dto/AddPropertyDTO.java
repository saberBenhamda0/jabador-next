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
        PricingDTO pricingDTO,
        int maxGuests,
        int bedrooms,
        int bathrooms,
        int rooms,
        int kitchens,
        LocationDTO locationDTO,
        long[] amenities,
        long owner,
        List<MediaDTO> images
) {}
