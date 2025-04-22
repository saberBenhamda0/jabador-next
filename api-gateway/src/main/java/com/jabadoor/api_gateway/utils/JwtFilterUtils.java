package com.jabadoor.api_gateway.utils;

import com.jabadoor.api_gateway.constants.SecurityConstants;
import com.jabadoor.api_gateway.jwt.JwtService;
import io.jsonwebtoken.Claims;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

@Component
public class JwtFilterUtils {
    private final JwtService jwtService;

    public JwtFilterUtils(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    public GatewayFilter validateJwtFilterAndAddUserIDToRequest() {
        return (exchange, chain) -> {
            try {
                String header = exchange.getRequest().getHeaders().getFirst(SecurityConstants.JWT_HEADER);
                if (header == null || !header.startsWith("Bearer ")) {
                    return Mono.error(new ResponseStatusException(
                            HttpStatus.BAD_REQUEST,
                            "Authorization header must start with 'Bearer '"
                    ));
                }

                String jwt = header.substring(7);
                if (jwtService.isTokenExpired(jwt)) {
                    return Mono.error(new ResponseStatusException(
                            HttpStatus.UNAUTHORIZED,
                            "Token expired"
                    ));
                }

                Claims claims = jwtService.extractAllCLaims(jwt);
                String userId = claims.get("user_id", String.class);

                ServerHttpRequest mutatedRequest = exchange.getRequest()
                        .mutate()
                        .header("user-id", userId)
                        .build();

                return chain.filter(exchange.mutate().request(mutatedRequest).build());
            } catch (Exception ex) {
                return Mono.error(new ResponseStatusException(
                        HttpStatus.UNAUTHORIZED,
                        "Invalid token"
                ));
            }
        };
    }
}