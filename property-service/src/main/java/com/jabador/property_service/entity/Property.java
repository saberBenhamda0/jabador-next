package com.jabador.property_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.id.factory.spi.GenerationTypeStrategy;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
}
