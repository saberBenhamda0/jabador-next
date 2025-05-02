package com.jabador.experience_service.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
public class Media {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String type;

    private String url;
    private String caption;
    private boolean isPrimary;
    private int imageOrder;

    @ManyToOne
    @JoinColumn(name = "experience_id")
    @JsonBackReference // Add this
    private Experience experience;

}
