package com.jabador.experience_service.controller;

import com.jabador.experience_service.entity.Experience;
import com.jabador.experience_service.service.ExperienceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/experience")
public class ExperienceController {

    private final ExperienceService experienceService;

    public ExperienceController(ExperienceService experienceService) {
        this.experienceService = experienceService;
    }

    @GetMapping("/all")
    public List<Experience> getAll(){
        return experienceService.findAll();
    }

    @GetMapping("/add")
    public String add(){
        experienceService.save();
        return "good";
    }
}
