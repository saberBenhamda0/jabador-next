package com.jabador.property_service.controller;

import com.jabador.property_service.entity.Amenitie;
import com.jabador.property_service.entity.Media;
import com.jabador.property_service.entity.Property;
import com.jabador.property_service.entity.UserCache;
import com.jabador.property_service.service.PropertyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/property")
public class PropertyController {

    private final PropertyService propertyService;

    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @GetMapping("/all")
    public List<Property> getAll(){
        return propertyService.getAll();
    }

    @GetMapping("/save")
    public String save(){
        propertyService.add();
        return  "everything we correct";
    }
}
