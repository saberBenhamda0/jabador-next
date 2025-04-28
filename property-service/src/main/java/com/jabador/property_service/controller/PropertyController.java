package com.jabador.property_service.controller;

import com.jabador.property_service.dto.AddPropertyDTO;
import com.jabador.property_service.dto.ListAllPropertyDTO;
import com.jabador.property_service.entity.Amenitie;
import com.jabador.property_service.entity.Media;
import com.jabador.property_service.entity.Property;
import com.jabador.property_service.entity.UserCache;
import com.jabador.property_service.service.PropertyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/property")
public class PropertyController {

    private final PropertyService propertyService;

    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @GetMapping("/all")
    public List<ListAllPropertyDTO> getAll(){
        return propertyService.getAll();
    }


    @PostMapping(value = "/save", consumes = {"multipart/form-data"})
    public Map<String, String> addProperty(@ModelAttribute AddPropertyDTO addPropertyDTO){
            return propertyService.add(addPropertyDTO);
    }

    @GetMapping("/{id}")
    public Property getProperty(@RequestParam long id){
        return propertyService.get(id);
    }
}
