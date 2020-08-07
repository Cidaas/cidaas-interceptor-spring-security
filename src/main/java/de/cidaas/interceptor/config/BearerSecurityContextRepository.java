package de.cidaas.interceptor.config;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final static Logger logger = LoggerFactory.getLogger(BearerSecurityContextRepository.class);

    @Override
    public SecurityContext loadContext(HttpRequestResponseHolder requestResponseHolder) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        
        String token = getTokenFromRequest(requestResponseHolder.getRequest());
        
        Authentication authentication = creatAuthenticationUsingToken(token);
        if (authentication != null) {
            context.setAuthentication(authentication);
            logger.debug("Found bearer token in request. Saving it in SecurityContext");
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
            logger.debug("No token was provided to build {}", JwtAuthentication.class.getName());
            return null;
        }
        try {
            DecodedJWT jwt = JWT.decode(token);
            return new JwtAuthentication(jwt);
        } catch (JWTDecodeException e) {
            logger.debug("Failed to decode token as jwt", e);
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
