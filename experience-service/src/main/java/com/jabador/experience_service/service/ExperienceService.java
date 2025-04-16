package com.jabador.experience_service.service;

import com.jabador.experience_service.repository.ExperienceRepository;
import org.springframework.stereotype.Service;

@Service
public class ExperienceService {

    private final ExperienceRepository experienceRepository;


    public ExperienceService(ExperienceRepository experienceRepository) {
        this.experienceRepository = experienceRepository;
    }
}
