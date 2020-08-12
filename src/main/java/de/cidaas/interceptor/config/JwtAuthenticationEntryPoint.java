package de.cidaas.interceptor.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.cidaas.model.ServerResponse;

public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {    
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		String json = new ObjectMapper().writeValueAsString(new ServerResponse("Access denied for this resource"));
		response.getWriter().write(json);
		response.flushBuffer();
    }
}
