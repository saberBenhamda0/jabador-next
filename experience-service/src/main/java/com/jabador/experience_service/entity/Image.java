package com.jabador.experience_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Image {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private double id;

    private String url;

    @ManyToOne
    @JoinColumn(name = "experienceId")
    private Experience experienceId;
}
