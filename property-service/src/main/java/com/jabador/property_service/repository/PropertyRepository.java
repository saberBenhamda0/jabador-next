package com.jabador.property_service.repository;

import com.jabador.property_service.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Integer > {
}
