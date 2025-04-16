package com.jabador.experience_service.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Embeddable
public class Details {

    private int prepTime;

    private int duration;

    private String startDate;

    private String endDate;

    private int[] timeSlots;

    private int minGroupSize;

    private int maxGroupSize;

    private boolean privateGroup;

    private String primaryLanguague;

    private boolean isWheelchairAccesible;
}
