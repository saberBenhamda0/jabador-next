package com.jabador.property_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
public class Location {

    private String continent;

    private String country;

    private String city;

    private String street;

    private double latitude;

    private double longitude;
}
