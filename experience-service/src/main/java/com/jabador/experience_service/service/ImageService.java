package com.jabador.experience_service.service;

import com.jabador.experience_service.entity.Experience;
import com.jabador.experience_service.entity.Image;
import com.jabador.experience_service.repository.ImageRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ImageService {

    private final ImageRepository imageRepository;

    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public List<Image> save(Experience experienceId){
        List<Image> images = new ArrayList<>();

        Image image = Image.builder()
                .url("test url")
                .experienceId(experienceId)
                .build();

        images.add(image);

        imageRepository.saveAll(images);
        return images;
    }
}
