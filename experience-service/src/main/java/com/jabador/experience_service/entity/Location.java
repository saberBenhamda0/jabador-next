package com.jabador.experience_service.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Location {

    private int[] meetingPoint;

    private int[] experienceLocation;

    private int codePostal;

    private String continent;

    private String country;

    private String city;

    private String street;
}
