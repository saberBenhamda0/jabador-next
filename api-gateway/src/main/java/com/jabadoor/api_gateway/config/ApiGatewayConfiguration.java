package com.jabadoor.api_gateway.config;

import com.jabadoor.api_gateway.constants.SecurityConstants;
import com.jabadoor.api_gateway.jwt.JwtService;
import com.jabadoor.api_gateway.utils.JwtFilterUtils;
import io.jsonwebtoken.Claims;
import jakarta.ws.rs.BadRequestException;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authentication.BadCredentialsException;
import reactor.netty.http.server.HttpServerRequest;

import java.util.function.Function;
import java.util.function.Predicate;

@Configuration
public class ApiGatewayConfiguration {

    private final JwtService jwtService;
    private final JwtFilterUtils jwtFilterUtils;

    public ApiGatewayConfiguration(JwtService jwtService, JwtFilterUtils jwtFilterUtils) {
        this.jwtService = jwtService;
        this.jwtFilterUtils = jwtFilterUtils;
    }

    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
        // you can't change the request you can just mutate or clone the request and modify it
        // then you forward it

        Function<PredicateSpec, Buildable<Route>> experienceRoute = p -> p
                .path("/api/experience/**")
                /*.filters(f -> { f
                        .filter(jwtFilterUtils.validateJwtFilterAndAddUserIDToRequest());
                    return f;
                })*/
                .uri("lb://experience-service");

        Function<PredicateSpec, Buildable<Route>> propertyRoute = p -> p
                .path("/api/property/**")
                /*
                .filters(f -> { f
                        .filter(jwtFilterUtils.validateJwtFilterAndAddUserIDToRequest());
                    return f;
                })*/
                .uri("lb://property-service");

        Function<PredicateSpec, Buildable<Route>> userRoute = p -> p
                .path("/api/user/**")
                /*.filters(f -> { f
                        .filter(jwtFilterUtils.validateJwtFilterAndAddUserIDToRequest());
                    return f;
                })*/
                .uri("lb://user-service");

        Function<PredicateSpec, Buildable<Route>> uploadsproperty = p -> p
                .path("/uploads/property/**")
                /*.filters(f -> { f
                        .filter(jwtFilterUtils.validateJwtFilterAndAddUserIDToRequest());
                    return f;
                })*/
                .uri("lb://property-service");

        Function<PredicateSpec, Buildable<Route>> authRoute = p -> p
                .path("/api/auth/**")
                .uri("lb://auth-service");


        return builder
                .routes()
                .route(experienceRoute)
                .route(userRoute)
                .route(authRoute)
                .route(uploadsproperty)
                .route(propertyRoute)
                .build();
    }
}
