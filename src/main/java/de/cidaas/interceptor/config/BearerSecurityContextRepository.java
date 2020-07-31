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

import de.cidaas.interceptor.authentication.JwtPreAuthentication;

public class BearerSecurityContextRepository implements SecurityContextRepository {
    private final static Logger logger = LoggerFactory.getLogger(BearerSecurityContextRepository.class);

    @Override
    public SecurityContext loadContext(HttpRequestResponseHolder requestResponseHolder) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        String token = tokenFromRequest(requestResponseHolder.getRequest());
        Authentication authentication = JwtPreAuthentication.usingToken(token);
        if (authentication != null) {
            context.setAuthentication(authentication);
            logger.debug("Found bearer token in request. Saving it in SecurityContext");
        }
        return context;
    }

    @Override
    public void saveContext(SecurityContext context, HttpServletRequest request, HttpServletResponse response) {
    	
    	 logger.info("Inside the saveContext of BearerSecurityContextRepository{}");
    }

    @Override
    public boolean containsContext(HttpServletRequest request) {
        return tokenFromRequest(request) != null;
    }

    private String tokenFromRequest(HttpServletRequest request) {
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
