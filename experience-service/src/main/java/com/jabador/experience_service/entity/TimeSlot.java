package com.jabador.experience_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class TimeSlot {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private double id;

    @ManyToOne
    @JoinColumn(name = "experienceId")
    private Experience experienceId;

    private double timeslot;
}
