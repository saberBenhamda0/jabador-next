package com.jabador.property_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Availability {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String status;

    private Instant StartDate;

    private Instant endDate;

    @ManyToOne
    @JoinColumn(name = "booker_id")
    private UserCache bookerId;

    @ManyToOne
    @JoinColumn(name = "property_id")
    private Property propertyId;
}
