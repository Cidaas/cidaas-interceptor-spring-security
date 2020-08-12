package de.cidaas.interceptor.config;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.springframework.security.core.AuthenticationException;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.cidaas.model.ServerResponse;

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
		
		String json = new ObjectMapper().writeValueAsString(new ServerResponse("Access denied for this resource"));
		
		verify(response, times(1)).getWriter();
		verify(response, times(1)).flushBuffer();
		verify(printWriter, times(1)).write(json);
	}
}
