package com.jabadoor.api_gateway.config;

import com.jabadoor.api_gateway.constants.SecurityConstants;
import com.jabadoor.api_gateway.jwt.JwtService;
import io.jsonwebtoken.Claims;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.reactive.ServerHttpRequest;
import reactor.netty.http.server.HttpServerRequest;

@Configuration
public class ApiGatewayConfiguration {

    private final JwtService jwtService;

    public ApiGatewayConfiguration(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
        // you can't change the request you can just mutate or clone the request and modify it
        // then you forward it
        return builder
                .routes()
                .route("user-service-route", r -> r
                        .path("/api/user/**")
                        .filters(f -> f
                                .filter((exchange, chain) -> {
                                    String myHeader = exchange.getRequest()
                                            .getHeaders()
                                            .getFirst(SecurityConstants.JWT_HEADER);

                                    if (myHeader != null && myHeader.startsWith("Bearer ")) {
                                        String jwt = myHeader.substring(7);
                                        Claims claims = jwtService.extractAllCLaims(jwt);
                                        String userId = claims.get("user_id", String.class);

                                        // Mutate the request to add user_id header
                                        ServerHttpRequest mutatedRequest = exchange.getRequest()
                                                .mutate()
                                                .header("user_id", userId)
                                                .build();

                                        return chain.filter(exchange.mutate().request(mutatedRequest).build());
                                    }

                                    return chain.filter(exchange); // proceed without mutation if no token
                                })
                        )
                        .uri("lb://user-service")
                )
                .route( "property-service", r ->
                        r.path("/api/property/**")
                                .filters(f ->
                                        f.filter((exchange, chain) -> {
                                            String header = exchange.getRequest().getHeaders().getFirst(SecurityConstants.JWT_HEADER);
                                            String jwt = header.substring(7);
                                                Claims claims = jwtService.extractAllCLaims(jwt);
                                                String userId = claims.get("user_id", String.class);
                                                ServerHttpRequest mutatedRequest = exchange.getRequest()
                                                        .mutate()
                                                        .header("user_id", userId)
                                                        .build();

                                                return chain.filter(exchange.mutate().request(mutatedRequest).build());
                                        })
                                )
                                                .uri("lb://user-service")

                )
                .build();
    }
}
