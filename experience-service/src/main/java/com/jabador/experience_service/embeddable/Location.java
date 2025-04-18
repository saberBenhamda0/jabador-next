package com.jabador.experience_service.embeddable;

import jakarta.persistence.*;
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

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "x", column = @Column( name = "meeting_point_x")),
            @AttributeOverride(name = "y", column = @Column( name = "meeting_point_y"))
    })
    private CoordinatePoint meetingPoint;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "x", column = @Column( name = "experience_location_x")),
            @AttributeOverride(name = "y", column = @Column( name = "experience_location_y"))
    })
    private CoordinatePoint experienceLocation;

    private int codePostal;

    private String continent;

    private String country;

    private String city;

    private String street;
}
