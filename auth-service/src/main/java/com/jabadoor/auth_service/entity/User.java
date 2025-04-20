package com.jabadoor.auth_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "user")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class User {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    private String firstName;

    private String secondName;

    private String email;

    private String phoneNumber;

    private String password;

    private String role;
}
