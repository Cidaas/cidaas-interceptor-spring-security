package de.cidaas.interceptor.config;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.web.context.HttpRequestResponseHolder;

import de.cidaas.interceptor.TokenHelperTest;

public class BearerSecurityContextRepositoryTest {
	
	HttpServletRequest request = null;
	HttpServletResponse response = null;
	SecurityContext context = null;
	HttpRequestResponseHolder reqRespHolder = null;
	BearerSecurityContextRepository bearerSecurityContextRepository = null;
	
	@Before
	public void setup() {
		request = mock(HttpServletRequest.class);
		response = mock(HttpServletResponse.class);
		context = mock(SecurityContext.class);
		reqRespHolder = new HttpRequestResponseHolder(request,response);
		bearerSecurityContextRepository = new BearerSecurityContextRepository();
	}
	
	@Test
	public void testLoadContext() {
		String  headerValue = "bearer " + TokenHelperTest.getTokenWithRolesAndScopes();
		when(request.getHeader("Authorization")).thenReturn(headerValue);
		
		assertNotNull(bearerSecurityContextRepository.loadContext(reqRespHolder));
	}
	
	@Test
	public void testLoadContextNegativeCase1() {
		when(request.getHeader("Authorization")).thenReturn(null);
		
		SecurityContext context = bearerSecurityContextRepository.loadContext(reqRespHolder);
		
		assertNotNull(context);
		assertNull(context.getAuthentication());
	}
	
	@Test
	public void testLoadContextNegativeCase2() {
		String  headerValue = "bearer-" + TokenHelperTest.getTokenWithRolesAndScopes();
		when(request.getHeader("Authorization")).thenReturn(headerValue);
		
		SecurityContext context = bearerSecurityContextRepository.loadContext(reqRespHolder);
		
		assertNotNull(context);
		assertNull(context.getAuthentication());
	}
	
	@Test
	public void testContainContext() {
		String  headerValue = "bearer " + TokenHelperTest.getTokenWithRolesAndScopes();
		when(request.getHeader("Authorization")).thenReturn(headerValue);
		
		assertTrue(bearerSecurityContextRepository.containsContext(request));
	}

	@Test
	public void testUsingInvalidToken() {
		assertNull(bearerSecurityContextRepository.creatAuthenticationUsingToken("abc.abc.abc"));
	}
	
	@Test
	public void testUsingNullValue() {
		assertNull(bearerSecurityContextRepository.creatAuthenticationUsingToken(null));
	}
	
	
	@Test
	public void testTokenExtraction() {
		when(request.getHeader("Authorization")).thenReturn("bearer " + TokenHelperTest.getTokenWithRolesAndScopes());
		assertEquals(bearerSecurityContextRepository.getTokenFromRequest(request), TokenHelperTest.getTokenWithRolesAndScopes());
		
		when(request.getHeader("Authorization")).thenReturn("WRONG " + TokenHelperTest.getTokenWithRolesAndScopes());
		assertNull(bearerSecurityContextRepository.getTokenFromRequest(request));
		
		when(request.getHeader("Authorization")).thenReturn("bearer-" + TokenHelperTest.getTokenWithRolesAndScopes());
		assertNull(bearerSecurityContextRepository.getTokenFromRequest(request));
	}
}
