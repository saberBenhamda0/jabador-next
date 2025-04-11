package com.jabador.property_service.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserCache {

    private String firstName;

    private String seconcName;

    private String email;

    private String gender;

    private String profilePicutreUrl;


}
