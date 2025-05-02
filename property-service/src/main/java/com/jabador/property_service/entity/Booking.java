package com.jabador.property_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String status;

    private String bookingType;

    @Embedded
    private Dates dates;

    @Embedded
    private Pricing price;

    @ManyToOne
    @JoinColumn(name = "property_id")
    private Property property;
}
