package com.jabador.property_service.dto;

public record LocationDTO(
        String continent,
        String country,
        String city,
        String street,
        int postalCode,
        double latitude,
        double longitude
) { }
