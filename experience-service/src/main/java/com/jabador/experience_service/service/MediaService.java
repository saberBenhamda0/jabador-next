package com.jabador.experience_service.service;

import com.jabador.experience_service.entity.Media;
import com.jabador.experience_service.entity.Experience;
import com.jabador.experience_service.repository.MediaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MediaService {

    private final MediaRepository mediaRepository;

    public MediaService(MediaRepository mediaRepository) {
        this.mediaRepository = mediaRepository;
    }


    public List<Media> save(Experience experienceId){
        List<Media> medias = new ArrayList<>();

        Media media = Media.builder()
                .url("test url")
                .experience(experienceId)
                .build();

        medias.add(media);

        mediaRepository.saveAll(medias);
        return medias;
    }

}