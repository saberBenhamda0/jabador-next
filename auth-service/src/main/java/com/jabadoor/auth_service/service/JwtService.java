package com.jabadoor.auth_service.service;

import com.jabadoor.auth_service.constants.SecurityConstants;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;

@Service
public class JwtService {

    public String generateAccessToken(Map<String, String> extraClaims, String subject){
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date((System.currentTimeMillis() + SecurityConstants.duration)))
                .signWith(getKey())
                .compact();
    }

    public String generateRefreshToken(Map<String, String> extraClaims, String subject){
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 4 * SecurityConstants.duration))
                .signWith(getKey())
                .compact();
    }

    private Key getKey(){
        byte[] key = SecurityConstants.JWT_KEY.getBytes();
        return Keys.hmacShaKeyFor(key);
    }
}
