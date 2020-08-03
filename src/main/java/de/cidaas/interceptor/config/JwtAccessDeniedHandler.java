package de.cidaas.interceptor.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.cidaas.model.ServerResponse;

public class JwtAccessDeniedHandler implements AccessDeniedHandler {
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {	
		response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		String json = new ObjectMapper().writeValueAsString(new ServerResponse("You don't have enough authorization to access this resource"));
		response.getWriter().write(json);
		response.flushBuffer();
	}

}
