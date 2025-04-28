package com.jabador.property_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> {
                    cors.disable();
                })
                .csrf(csrf -> {
                    csrf.disable();
                })
                .formLogin(httpSecurityFormLoginConfigurer -> {
            httpSecurityFormLoginConfigurer.disable();
        })
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll() // allow all requests without authentication
                )
                .httpBasic(httpSecurityHttpBasicConfigurer -> {
                    httpSecurityHttpBasicConfigurer.disable();
                });
        return http.build();
    }
}
