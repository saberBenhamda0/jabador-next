package com.jabadoor.api_gateway.jwt;

import com.jabadoor.api_gateway.constants.SecurityConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.StringJoiner;

@Service
public class JwtService {


    public Claims extractAllCLaims(String token){
        return Jwts.parserBuilder().setSigningKey(getSecretKey()).build().parseClaimsJws(token).getBody();
    }

    public Key getSecretKey(){
        String secretKey = SecurityConstants.JWT_SIGNIN_KEY;
        byte[] key = secretKey.getBytes();
        return Keys.hmacShaKeyFor(key);
    }

    public boolean isTokenExpired(String token){
        Claims claims = extractAllCLaims(token);
        return claims.getExpiration().before(new Date(System.currentTimeMillis()));
    }

}
