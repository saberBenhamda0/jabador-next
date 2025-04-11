package com.jabador.property_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "properties") // Good practice to explicitly name tables
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, length = 50)
    private String type;

    private String description;

    @Embedded
    private UserCache owner;

    // features
    private Integer rooms;

    @Column(name = "bed_rooms")
    private Integer bedRooms;

    private Integer kitchens;
    private Integer bathrooms;

    @Column(name = "max_guests")
    private Integer maxGuests;

    @ManyToMany
    @JoinTable(
            name = "property_amenities",
            joinColumns = @JoinColumn(name = "property_id"),
            inverseJoinColumns = @JoinColumn(name = "amenity_id")
    )
    private List<Amenitie> amenities;


    @OneToMany
    @JoinColumn(
            name = "property_id"
    )
    private List<Media> media;

    @Embedded
    private Pricing pricing;

    @Embedded
    private Location location;
}