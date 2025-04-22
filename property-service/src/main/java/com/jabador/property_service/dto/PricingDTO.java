package com.jabador.property_service.dto;

public record PricingDTO(
        double basePrice,

        double cleaningFee,

         double serviceFee,

        double taxes,

         String currency

) {}
