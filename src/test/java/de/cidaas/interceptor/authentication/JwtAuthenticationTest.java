package de.cidaas.interceptor.authentication;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import de.cidaas.interceptor.BaseSpringInterceptorTest;
import de.cidaas.jwt.JWT;
import de.cidaas.jwt.interfaces.DecodedJWT;

public class JwtAuthenticationTest {

	@Test
	@SuppressWarnings("unchecked")
	public void testGetAuthoritiesWithoutRoles() {
		DecodedJWT jwt = JWT.decode(BaseSpringInterceptorTest.getTokenWithoutRoles());
		
		JwtAuthentication jwtAuth = new JwtAuthentication(jwt);
		
		assertFalse(jwtAuth.isAuthenticated());
		assertEquals(jwtAuth.getName(), jwt.getSubject());
		assertEquals(jwtAuth.getPrincipal(), jwt.getSubject());
		assertEquals(jwtAuth.getCredentials(), jwt);
		
		
		List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("Testscope"));
		
        assertEquals((List<SimpleGrantedAuthority>) jwtAuth.getAuthorities(), authorities);
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void testGetAuthoritiesWithRolesAndScopes() {
		DecodedJWT jwt = JWT.decode(BaseSpringInterceptorTest.getTokenWithRolesAndScopes());
		
		JwtAuthentication jwtAuth = new JwtAuthentication(jwt);
		
		assertFalse(jwtAuth.isAuthenticated());
		assertEquals(jwtAuth.getName(), jwt.getSubject());
		assertEquals(jwtAuth.getPrincipal(), jwt.getSubject());
		assertEquals(jwtAuth.getCredentials(), jwt);
		
		List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("cidaas:userinfo"));
		authorities.add(new SimpleGrantedAuthority("cidaas:write"));
		authorities.add(new SimpleGrantedAuthority("cidaas:read"));
		authorities.add(new SimpleGrantedAuthority("offline_access"));
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		authorities.add(new SimpleGrantedAuthority("ROLE_BOSCH_ADMIN"));
		authorities.add(new SimpleGrantedAuthority("ROLE_BOSCH_SUPER_ADMIN"));
		authorities.add(new SimpleGrantedAuthority("ROLE_BOSCH_USER"));
		
        assertEquals((List<SimpleGrantedAuthority>) jwtAuth.getAuthorities(), authorities);
	}
}
