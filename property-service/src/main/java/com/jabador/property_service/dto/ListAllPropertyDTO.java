package com.jabador.property_service.dto;

import com.jabador.property_service.entity.Media;
import lombok.Builder;

import java.util.List;

@Builder
public record ListAllPropertyDTO(
        long id,
        String name,
        String type,
        double price,
        LocationDTO location,
        List<Media> images
)
{}
