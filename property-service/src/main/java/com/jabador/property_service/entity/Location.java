package com.jabador.property_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Location {

    private String country;

    private String city;

    private String street;

    private int postalCode;

    private double latitude;

    private double longitude;
}
