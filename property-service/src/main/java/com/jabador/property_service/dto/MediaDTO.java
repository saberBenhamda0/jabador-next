package com.jabador.property_service.dto;

import org.springframework.web.multipart.MultipartFile;

public record MediaDTO(

        MultipartFile image,

        String caption,

        String contentType,

        String name,

        boolean isPrimary,

        int order,

        String type
) {}
