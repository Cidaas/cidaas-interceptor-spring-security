package de.cidaas.interceptor.config;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpRequestResponseHolder;
import org.springframework.security.web.context.SecurityContextRepository;

import de.cidaas.jwt.JWT;
import de.cidaas.jwt.exceptions.JWTDecodeException;
import de.cidaas.jwt.interfaces.DecodedJWT;
import de.cidaas.model.JwtAuthentication;

public class BearerSecurityContextRepository implements SecurityContextRepository {

    @Override
    public SecurityContext loadContext(HttpRequestResponseHolder requestResponseHolder) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        
        String token = getTokenFromRequest(requestResponseHolder.getRequest());
        
        Authentication authentication = creatAuthenticationUsingToken(token);
        if (authentication != null) {
            context.setAuthentication(authentication);
        }
        return context;
    }

    @Override
    public void saveContext(SecurityContext context, HttpServletRequest request, HttpServletResponse response) {
    }

    @Override
    public boolean containsContext(HttpServletRequest request) {
        return getTokenFromRequest(request) != null;
    }
    
    public JwtAuthentication creatAuthenticationUsingToken(String token) {
        if (token == null) {
            return null;
        }
        try {
            DecodedJWT jwt = JWT.decode(token);
            return new JwtAuthentication(jwt);
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    public String getTokenFromRequest(HttpServletRequest request) {
        final String value = request.getHeader("Authorization");

        if (value == null || !value.toLowerCase().startsWith("bearer")) {
            return null;
        }

        String[] parts = value.split(" ");

        if (parts.length < 2) {
            return null;
        }

        return parts[1].trim();
    }
}
