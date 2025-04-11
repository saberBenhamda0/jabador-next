package com.jabador.property_service.repository;

import com.jabador.property_service.entity.Amenitie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AmenitieRepository extends JpaRepository<Amenitie, Double> {
}
