package com.jabador.property_service.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDateTime;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Dates {

    private long bookingId;

    private Instant bookingCreatedAt;

    private Instant checkIn;

    private Instant checkOut;

    private int numberOfNights;

}
