package com.jabadoor.api_gateway.filter;

import com.jabadoor.api_gateway.jwt.JwtService;
import io.jsonwebtoken.Claims;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class JwtWebFilter implements WebFilter {

    private final JwtService jwtService;
    private static final List<String> PUBLIC_PATHS = List.of(
            "/api/auth/",
            "/public/",
            "/actuator/",
            "/v3/api-docs",
            "/swagger-ui",
            "/webjars"
    );

    public JwtWebFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        String path = exchange.getRequest().getPath().toString();

        if (shouldBypassAuth(path)) {
            return chain.filter(exchange);
        }

        String authHeader = exchange.getRequest().getHeaders().getFirst("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return unauthorizedResponse(exchange, "Missing or invalid Authorization header");
        }

        String token = authHeader.substring(7).trim();
        if (token.isEmpty()) {
            return unauthorizedResponse(exchange, "Empty token");
        }

        try {
            if (jwtService.isTokenExpired(token)) {
                return unauthorizedResponse(exchange, "Token expired");
            }


            Claims claims = jwtService.extractAllCLaims(token);
            ServerHttpRequest mutatedRequest = exchange.getRequest().mutate()
                    .header("X-User-ID", String.valueOf(claims.get("sub")))
                    .header("X-User-Roles", String.valueOf(claims.get("roles")))
                    .header("X-Token-Issued-At", String.valueOf(claims.getIssuedAt()))
                    .build();

            return chain.filter(exchange.mutate().request(mutatedRequest).build());

        } catch (Exception e) {
            return unauthorizedResponse(exchange, "Token validation failed: " + e.getMessage());
        }
    }

    private boolean shouldBypassAuth(String path) {
        return PUBLIC_PATHS.stream().anyMatch(path::startsWith);
    }

    private Mono<Void> unauthorizedResponse(ServerWebExchange exchange, String message) {
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        exchange.getResponse().getHeaders().add("X-Auth-Error", message);
        return exchange.getResponse().setComplete();
    }
}