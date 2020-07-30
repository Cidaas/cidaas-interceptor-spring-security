package de.cidaas.interceptor.config;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import de.cidaas.interceptor.authentication.AuthenticationJsonWebToken;
import de.cidaas.interceptor.authentication.JwtAuthentication;
import de.cidaas.interceptor.authentication.provider.OfflineAuthenticationProvider;
import de.cidaas.jwk.JwkProvider;
import de.cidaas.jwt.JWTVerifier;
import de.cidaas.jwt.algorithms.Algorithm;
import de.cidaas.jwt.exceptions.JWTVerificationException;
import de.cidaas.jwt.interfaces.Clock;


public class JwtAuthenticationProviderTest  {
	
	JwkProvider provider = null;
	OfflineAuthenticationProvider authenticationProvider = null;
	Authentication authentication = null;
	Authentication jwtAuth = null;
	JwtAuthentication jwtAuthentication = null;
	Algorithm algo = null;
	Clock clock = null;
	
	@org.powermock.core.classloader.annotations.Mock
	private JWTVerifier verifier;
	
	
	
	@Before
	public void setUp() {
		byte[] byteArray = {};
		
		 authenticationProvider = new OfflineAuthenticationProvider(byteArray, "mock", "mock");
		// provider = mock(JwkProvider.class);
		// authenticationProvider2 = new JwtAuthenticationProvider(provider, "mock", "mock");
		 authentication = mock(Authentication.class);
		 jwtAuth = mock(AuthenticationJsonWebToken.class);
		 jwtAuthentication = mock(JwtAuthentication.class);
		 algo=  mock(Algorithm.class);
		 clock = mock(Clock.class);
		 when(jwtAuthentication.verify(verifier)).thenReturn(authentication);
		 when(jwtAuthentication.verify(null)).thenReturn(authentication);
	}
	
	
	@Test
	public void testSupports() {
		assertTrue(authenticationProvider.supports(JwtAuthentication.class));
	}
	
	
	@Test
	public void testAuthenticate() {
		jwtAuthentication = (JwtAuthentication) jwtAuth;
		when(jwtAuthentication.verify(Mockito.any(JWTVerifier.class))).thenReturn(authentication);
		assertNotNull(authenticationProvider.authenticate(jwtAuth));
		
	}
	@Test(expected = BadCredentialsException.class)
	public void testAuthenticateException() {
		jwtAuthentication = (JwtAuthentication) jwtAuth;
		Mockito.doThrow(JWTVerificationException.class).when(jwtAuthentication).verify(Mockito.any(JWTVerifier.class));
		assertNotNull(authenticationProvider.authenticate(jwtAuth));
		
	}
}
