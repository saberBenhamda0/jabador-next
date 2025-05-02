package com.jabador.experience_service.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class TimeSlot {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    private String date;

    private String dayOfWeek;

    private String formatedDate;

    private String startTime;

    private String endTime;

    private int originalPrice;

    private int discountedPrice;

    private String currency;

    private int availableSpots;

    private boolean isSpecialOffer;


    @ManyToOne
    @JoinColumn(name = "experience_id")
    @JsonBackReference // Add this
    private Experience experience;
}
