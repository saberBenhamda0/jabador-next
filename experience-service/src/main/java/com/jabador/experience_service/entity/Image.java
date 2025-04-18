package com.jabador.experience_service.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
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
    private Long id;

    private String url;

    @ManyToOne
    @JoinColumn(name = "experienceId")
    @JsonBackReference
    private Experience experience;
}
