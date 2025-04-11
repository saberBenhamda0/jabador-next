package com.jabador.property_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Pricing {

    private double basePrice;

    private double cleaningFee;

    private double serviceFee;

    private double taxes;

    private String currency;

}
