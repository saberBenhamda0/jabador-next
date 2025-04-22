package com.jabador.property_service.dto;

import org.springframework.web.multipart.MultipartFile;

public record MediaDTO(

        MultipartFile image,

        boolean isPrimary,

        int imagesOrder
) {}
