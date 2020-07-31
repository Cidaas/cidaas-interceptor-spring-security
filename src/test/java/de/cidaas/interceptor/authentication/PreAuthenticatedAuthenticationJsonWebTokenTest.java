package de.cidaas.interceptor.authentication;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import de.cidaas.jwt.interfaces.DecodedJWT;

public class PreAuthenticatedAuthenticationJsonWebTokenTest {
	
	JwtPreAuthentication jsonWebToken = null;

	@Before
	public void test() {
		
		jsonWebToken = this.getPreAuthJsonWebToken();
	}
	
	
	
	
	
	/**
	 * @return
	 */
	private JwtAuthentication getMockedAuthJsonWebInstance() {
		String token = "eyJhbGciOiJSUzI1NiIsImtpZCI6IjAxZTM2YmFkLWQ3M2MtNGY1ZC05NDkxLTFjZjVjYTFhMDA3OSJ9.eyJhbXIiOlsiMiJdLCJ1YV9oYXNoIjoiNzY2NGViZWZkMWM5NDRlNzcxZWI4ZDBlODI2ZmFjMDIiLCJzaWQiOiJhZjg3NGY4YS02MjlhLTRiOGMtYTZmNC01ZmFhZmQ0ODg2MDEiLCJzdWIiOiI2ZDU4Yjg3Mi0xMzgwLTQ1OWMtOWFiMS1mMmZlZWY4MjA3YTIiLCJpc3ViIjoiNzI2NTExM2EtZDc3Ny00MTAxLThlMjItOGE2MWYwM2JjNjk4IiwiYXVkIjoiYTNkMGM2NGMtZDYyOS00NjQ2LWFlZGYtODgyODBiYWQ4MjcxIiwiaWF0IjoxNTkxMjgzMzQ3LCJhdXRoX3RpbWUiOjE1OTEyODMzNDcsImlzcyI6Imh0dHBzOi8vYm9zY2gtdGVzdC5jaWRhYXMuZGUiLCJqdGkiOiI5ZjliYzZhYy1kMjkyLTRkNDktOTA4My1kZGM5ZDIzYzhmNzQiLCJzY29wZXMiOlsiY2lkYWFzOnVzZXJpbmZvIiwiY2lkYWFzOndyaXRlIiwiY2lkYWFzOnJlYWQiLCJvZmZsaW5lX2FjY2VzcyJdLCJyb2xlcyI6WyJVU0VSIiwiQk9TQ0hfQURNSU4iLCJCT1NDSF9TVVBFUl9BRE1JTiIsIkJPU0NIX1VTRVIiXSwiZ3JvdXBzIjpbeyJncm91cElkIjoiQ0lEQUFTX0FETUlOUyIsInJvbGVzIjpbIkFETUlOIl19XSwiZXhwIjoxNTkxMzY5NzQ3fQ.i95T_W86rwnXdoiJB3KG8Ptx2AMxtc11LeVu83_YyRvKdqJ8-vb3jkTdbCZPrIKjCLuaLVeZ3SG1x-FGHkcZWUoA4k9BRI7J8aN1H04DbxG9Zikj_NSo1pKKMWZzFb7kfmRBd98rUqyq_5GePX02OB1ofBAalvKMhSa9QHD8s4E";
		JwtAuthentication authenticationJsonWebToken = new JwtAuthentication(token, null);
		return authenticationJsonWebToken;
	}
	
	
	
	@Test
	public void testGetCredentials() {
		assertNotNull(jsonWebToken.getCredentials());
		
	}
	
	
	@Test
	public void testGetAuthorities() {
		List<Object> emptyList = Collections.emptyList();
		assertEquals(emptyList, jsonWebToken.getAuthorities());
	}
	
	
	@Test
	public void testPreAuthenticatedWebToken() {
		String token = "eyJhbGciOiJSUzI1NiIsImtpZCI6IjAxZTM2YmFkLWQ3M2MtNGY1ZC05NDkxLTFjZjVjYTFhMDA3OSJ9.eyJhbXIiOlsiMiJdLCJ1YV9oYXNoIjoiNzY2NGViZWZkMWM5NDRlNzcxZWI4ZDBlODI2ZmFjMDIiLCJzaWQiOiJhZjg3NGY4YS02MjlhLTRiOGMtYTZmNC01ZmFhZmQ0ODg2MDEiLCJzdWIiOiI2ZDU4Yjg3Mi0xMzgwLTQ1OWMtOWFiMS1mMmZlZWY4MjA3YTIiLCJpc3ViIjoiNzI2NTExM2EtZDc3Ny00MTAxLThlMjItOGE2MWYwM2JjNjk4IiwiYXVkIjoiYTNkMGM2NGMtZDYyOS00NjQ2LWFlZGYtODgyODBiYWQ4MjcxIiwiaWF0IjoxNTkxMjgzMzQ3LCJhdXRoX3RpbWUiOjE1OTEyODMzNDcsImlzcyI6Imh0dHBzOi8vYm9zY2gtdGVzdC5jaWRhYXMuZGUiLCJqdGkiOiI5ZjliYzZhYy1kMjkyLTRkNDktOTA4My1kZGM5ZDIzYzhmNzQiLCJzY29wZXMiOlsiY2lkYWFzOnVzZXJpbmZvIiwiY2lkYWFzOndyaXRlIiwiY2lkYWFzOnJlYWQiLCJvZmZsaW5lX2FjY2VzcyJdLCJyb2xlcyI6WyJVU0VSIiwiQk9TQ0hfQURNSU4iLCJCT1NDSF9TVVBFUl9BRE1JTiIsIkJPU0NIX1VTRVIiXSwiZ3JvdXBzIjpbeyJncm91cElkIjoiQ0lEQUFTX0FETUlOUyIsInJvbGVzIjpbIkFETUlOIl19XSwiZXhwIjoxNTkxMzY5NzQ3fQ.i95T_W86rwnXdoiJB3KG8Ptx2AMxtc11LeVu83_YyRvKdqJ8-vb3jkTdbCZPrIKjCLuaLVeZ3SG1x-FGHkcZWUoA4k9BRI7J8aN1H04DbxG9Zikj_NSo1pKKMWZzFb7kfmRBd98rUqyq_5GePX02OB1ofBAalvKMhSa9QHD8s4E";
		assertNotNull(JwtPreAuthentication.usingToken(token));
		
	}
	
	@Test
	public void testPreAuthenticatedWebTokenForNull() {
		assertNull(JwtPreAuthentication.usingToken(null));
		
	}
	
	@Test
	public void testPreAuthenticatedWebTokenForException() {
		assertNull(JwtPreAuthentication.usingToken("dummy"));
		
	}
	
	
	@Test
	public void testGetToken() {
		assertNotNull(jsonWebToken.getToken());
		
	}
	
	@Test
	public void testGetPrincipal() {
		assertNotNull(jsonWebToken.getPrincipal());
		
	}
	
	@Test
	public void testGetKeyId() {
		assertNotNull(jsonWebToken.getKeyId());
		
	}
	
	@Test
	public void testGetName() {
		assertNotNull(jsonWebToken.getName());
		
	}
	
	
	@Test
	public void testGetDetails() {
		assertNotNull(jsonWebToken.getDetails());
		
	}
	
	@Test
	public void testIsAuthenticated() {
		
		assertFalse(jsonWebToken.isAuthenticated());
		
	}
	
	public void testSetAuthenticated() {
		jsonWebToken.setAuthenticated(false);
	}





	/**
	 * @return
	 */
	private JwtPreAuthentication getPreAuthJsonWebToken() {
		DecodedJWT token = (DecodedJWT) this.getMockedAuthJsonWebInstance().getDetails();
		JwtPreAuthentication jsonWebToken = new JwtPreAuthentication(token);
		return jsonWebToken;
	}

}
