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

    @ManyToOne
    @JoinColumn(name = "experienceId")
    @JsonBackReference
    private Experience experience;

    private double timeslot;
}
