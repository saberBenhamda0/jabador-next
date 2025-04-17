package com.jabador.experience_service.entity;

import com.jabador.experience_service.embeddable.Details;
import com.jabador.experience_service.embeddable.Guest;
import com.jabador.experience_service.embeddable.Location;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Experience {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String title;

    private String description;

    @OneToMany(mappedBy = "experienceId")
    private List<Image> mainImages;

    @OneToMany(mappedBy = "experienceId")
    private List<TimeSlot> timeSlot;

    @Embedded
    private Guest guest;

    @Embedded
    private Details details;

    @Embedded
    private Location location;

    private int pricing;

    


}
