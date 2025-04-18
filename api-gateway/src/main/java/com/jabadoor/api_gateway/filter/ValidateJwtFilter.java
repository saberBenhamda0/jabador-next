package com.jabadoor.api_gateway.filter;

import com.jabadoor.api_gateway.constants.SecurityConstants;
import com.jabadoor.api_gateway.jwt.JwtService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import java.io.IOException;

@Component
public class ValidateJwtFilter extends OncePerRequestFilter {

    private final JwtService  jwtService;

    @Autowired
    public ValidateJwtFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }


    public boolean shouldNotFilter(HttpServletRequest request){
        return request.getServletPath().startsWith("/api/auth");
    }

    @Override
    protected void doFilterInternal(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response, jakarta.servlet.FilterChain filterChain) throws ServletException, IOException {
        try {
            String header = request.getHeader(SecurityConstants.JWT_HEADER);
            if(header == null || header.isEmpty()){
                throw new BadRequestException("there is no correct header in the request");
            }

            try {
                String jwt = header.substring(7);
                if(jwtService.isTokenExpired(jwt)){
                    throw new BadRequestException("the jwt is invalid");
                }
                filterChain.doFilter(request, response);
            } catch (BadRequestException e){
                e.getMessage();
                e.getStackTrace();
            }
        }
        catch (BadRequestException e){
            e.getMessage();
            e.getStackTrace();
        }
    }
}
