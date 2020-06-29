package de.cidaas.interceptor.config;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.springframework.security.core.AuthenticationException;

public class JwtAuthenticationEntryPointTest {
	
	PrintWriter printWriter = null;
	AuthenticationException authException = null;
	HttpServletRequest request = null;
	HttpServletResponse response = null;
	
	
	
	@Before
	public void setUp() throws IOException {
		printWriter = mock(PrintWriter.class);
		authException = mock(AuthenticationException.class);
		request = mock(HttpServletRequest.class);
		response = mock(HttpServletResponse.class);
		when(response.getWriter()).thenReturn(printWriter);
	}
	
	
	@Test
	public void testCommence() throws IOException, ServletException {
		JwtAuthenticationEntryPoint authenticationEntryPoint = new JwtAuthenticationEntryPoint();
		authenticationEntryPoint.commence(request, response, authException);
		
	}

	

}
