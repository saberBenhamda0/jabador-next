package com.jabador.property_service.dto;

public record PricingDTO(
        double basePrice,

        double taxes,

         String currency

) {}
