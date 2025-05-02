package com.jabador.experience_service.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.jabador.experience_service.embeddable.*;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)  // Added to prevent circular equals/hashCode
public class Experience {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Changed from AUTO to IDENTITY
    @EqualsAndHashCode.Include  // Added for proper equals/hashCode
    private Long id;  // Changed from primitive long to object Long

    private String title;
    private String description;

    @Embedded
    private Categorie categorie;

    @OneToMany()
    private List<Media> media;

    @OneToMany()
    private List<TimeSlot> timeSlot;  // Changed to plural and corrected mapping

    @Embedded
    private Guest guest;

    @Embedded
    private Details details;

    @Embedded
    private Location location;

    @Embedded
    private Pricing pricing;

}
