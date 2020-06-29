package de.cidaas.interceptor.authentication;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import de.cidaas.jwt.JWTVerifier;


public class AuthenticationJsonWebTokenTest {
	
	private JWTVerifier verifier;
	
	
	

	@Before
	public void test() {
		//MockitoAnnotations.initMocks(this);
	}
	
	
	
	@Test
	public void testGetToken() {
		
		AuthenticationJsonWebToken authenticationJsonWebToken = getMockedAuthJsonWebInstance();
		assertNotNull(authenticationJsonWebToken.getToken());
		
	}



	/**
	 * @return
	 */
	private AuthenticationJsonWebToken getMockedAuthJsonWebInstance() {
		String token = "eyJhbGciOiJSUzI1NiIsImtpZCI6IjAxZTM2YmFkLWQ3M2MtNGY1ZC05NDkxLTFjZjVjYTFhMDA3OSJ9.eyJhbXIiOlsiMiJdLCJ1YV9oYXNoIjoiNzY2NGViZWZkMWM5NDRlNzcxZWI4ZDBlODI2ZmFjMDIiLCJzaWQiOiJhZjg3NGY4YS02MjlhLTRiOGMtYTZmNC01ZmFhZmQ0ODg2MDEiLCJzdWIiOiI2ZDU4Yjg3Mi0xMzgwLTQ1OWMtOWFiMS1mMmZlZWY4MjA3YTIiLCJpc3ViIjoiNzI2NTExM2EtZDc3Ny00MTAxLThlMjItOGE2MWYwM2JjNjk4IiwiYXVkIjoiYTNkMGM2NGMtZDYyOS00NjQ2LWFlZGYtODgyODBiYWQ4MjcxIiwiaWF0IjoxNTkxMjgzMzQ3LCJhdXRoX3RpbWUiOjE1OTEyODMzNDcsImlzcyI6Imh0dHBzOi8vYm9zY2gtdGVzdC5jaWRhYXMuZGUiLCJqdGkiOiI5ZjliYzZhYy1kMjkyLTRkNDktOTA4My1kZGM5ZDIzYzhmNzQiLCJzY29wZXMiOlsiY2lkYWFzOnVzZXJpbmZvIiwiY2lkYWFzOndyaXRlIiwiY2lkYWFzOnJlYWQiLCJvZmZsaW5lX2FjY2VzcyJdLCJyb2xlcyI6WyJVU0VSIiwiQk9TQ0hfQURNSU4iLCJCT1NDSF9TVVBFUl9BRE1JTiIsIkJPU0NIX1VTRVIiXSwiZ3JvdXBzIjpbeyJncm91cElkIjoiQ0lEQUFTX0FETUlOUyIsInJvbGVzIjpbIkFETUlOIl19XSwiZXhwIjoxNTkxMzY5NzQ3fQ.i95T_W86rwnXdoiJB3KG8Ptx2AMxtc11LeVu83_YyRvKdqJ8-vb3jkTdbCZPrIKjCLuaLVeZ3SG1x-FGHkcZWUoA4k9BRI7J8aN1H04DbxG9Zikj_NSo1pKKMWZzFb7kfmRBd98rUqyq_5GePX02OB1ofBAalvKMhSa9QHD8s4E";
		AuthenticationJsonWebToken authenticationJsonWebToken = new AuthenticationJsonWebToken(token, verifier);
		return authenticationJsonWebToken;
	}
	
	@Test
	public void testGetKeyId() {
		
		AuthenticationJsonWebToken authenticationJsonWebToken = getMockedAuthJsonWebInstance();
		assertNotNull(authenticationJsonWebToken.getKeyId());
		
	}
	
	@Test
	public void testGetName() {
		
		AuthenticationJsonWebToken authenticationJsonWebToken = getMockedAuthJsonWebInstance();
		assertNotNull(authenticationJsonWebToken.getName());
		
	}
	
	@Test
	public void testGetPrincipal() {
		
		AuthenticationJsonWebToken authenticationJsonWebToken = getMockedAuthJsonWebInstance();
		assertNotNull(authenticationJsonWebToken.getPrincipal());
		
	}
	
	@Test
	public void testIsAuthenticated() {
		
		AuthenticationJsonWebToken authenticationJsonWebToken = getMockedAuthJsonWebInstance();
		assertFalse(authenticationJsonWebToken.isAuthenticated());
		
	}
	
	@Test
	public void testGetAuthorities() {
		AuthenticationJsonWebToken authenticationJsonWebToken = getMockedAuthJsonWebInstance();
		assertNotNull(authenticationJsonWebToken.getAuthorities());
		
	}
	
	@Test
	public void testGetCredentials() {
		AuthenticationJsonWebToken authenticationJsonWebToken = getMockedAuthJsonWebInstance();
		assertNotNull(authenticationJsonWebToken.getCredentials());
	}
	
	@Test
	public void testSetAuthenticated() {
		AuthenticationJsonWebToken authenticationJsonWebToken = getMockedAuthJsonWebInstance();
		authenticationJsonWebToken.setAuthenticated(false);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSetAuthenticatedException() {
		AuthenticationJsonWebToken authenticationJsonWebToken = getMockedAuthJsonWebInstance();
		authenticationJsonWebToken.setAuthenticated(true);
	}
	
	@Test
	public void testGetDetails() {
		AuthenticationJsonWebToken authenticationJsonWebToken = getMockedAuthJsonWebInstance();
		assertNotNull(authenticationJsonWebToken.getDetails());
	}
	
	
	
	@Test
	public void testVerify() {
		AuthenticationJsonWebToken authenticationJsonWebToken = getMockedAuthJsonWebInstance();
		assertNotNull(authenticationJsonWebToken.verify(verifier));
	}

}
