package de.cidaas.interceptor.config;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.web.context.HttpRequestResponseHolder;

import de.cidaas.interceptor.authentication.JwtAuthentication;

public class BearerSecurityContextRepositoryTest {
	
	HttpServletRequest request = null;
	HttpServletResponse response = null;
	SecurityContext context = null;
	
	
	@Before
	public void setup() {
		String  headerValue = "bearer eyJhbGciOiJSUzI1NiIsImtpZCI6IjAxZTM2YmFkLWQ3M2MtNGY1ZC05NDkxLTFjZjVjYTFhMDA3OSJ9.eyJhbXIiOlsiMiJdLCJ1YV9oYXNoIjoiNzY2NG"
				+ "ViZWZkMWM5NDRlNzcxZWI4ZDBlODI2ZmFjMDIiLCJzaWQiOiJhZjg3NGY4YS02MjlhLTRiOGMtYTZmNC01ZmFhZmQ0ODg2MDEiLCJzdWIiOiI2ZDU4Yjg3Mi0xMzgwLTQ1OWMtOWFiMS"
				+ "1mMmZlZWY4MjA3YTIiLCJpc3ViIjoiNzI2NTExM2EtZDc3Ny00MTAxLThlMjItOGE2MWYwM2JjNjk4IiwiYXVkIjoiYTNkMGM2NGMtZDYyOS00NjQ2LWFlZGYtODgyODBiYWQ4MjcxIiwiaWF0IjoxNTkxMjgzMzQ3LCJhdXRoX3"
				+ "RpbWUiOjE1OTEyODMzNDcsImlzcyI6Imh0dHBzOi8vYm9zY2gtdGVzdC5jaWRhYXMuZGUiLCJqdGkiOiI5ZjliYzZhYy1kMjkyLTRkNDktOTA4My1kZGM5ZDIzYzhmNzQiLCJzY29wZXMiOlsiY2lkYWFzOnVzZXJpbmZvIiwiY2lkYWFzOndya"
				+ "XRlIiwiY2lkYWFzOnJlYWQiLCJvZmZsaW5lX2FjY2VzcyJdLCJyb2xlcyI6WyJVU0VSIiwiQk9TQ0hfQURNSU4iLCJCT1NDSF9TVVBFUl9BRE1JTiIsIkJPU0NIX1VTRVIiXSwiZ3JvdXBzIjpbeyJncm91cElkIjoiQ0lEQUFTX0FETUlOUyIsIn"
				+ "JvbGVzIjpbIkFETUlOIl19XSwiZXhwIjoxNTkxMzY5NzQ3fQ.i95T_W86rwnXdoiJB3KG8Ptx2AMxtc11LeVu83_YyRvKdqJ8-vb3jkTdbCZPrIKjCLuaLVeZ3SG1x-FGHkcZWUoA4k9BRI7J8aN1H04DbxG9Zikj_NSo1pKKMWZzFb7kfmRBd98rUqyq_5GePX02OB"
				+ "1ofBAalvKMhSa9QHD8s4E";
		request = mock(HttpServletRequest.class);
		response = mock(HttpServletResponse.class);
		context = mock(SecurityContext.class);
		when(request.getHeader("Authorization")).thenReturn(headerValue);
	}
	
	
	
	@Test
	public void testLoadContext() {
		
		BearerSecurityContextRepository bearerSecurityContextRepository = new BearerSecurityContextRepository();
		HttpRequestResponseHolder req = new HttpRequestResponseHolder(request,response);
		//when(req.getRequest()).thenReturn(request);
		assertNotNull(bearerSecurityContextRepository.loadContext(req));
		
		
	}
	
	@Test
	public void testLoadContextNegativeCase1() {
		String  headerValue = "bearer eyJhbGciOiJSUzI1NiIsImtpZCI6IjAxZTM2YmFkLWQ3M2MtNGY1ZC05NDkxLTFjZjVjYTFhMDA3OSJ9.eyJhbXIiOlsiMiJdLCJ1YV9oYXNoIjoiNzY2NG"
				+ "ViZWZkMWM5NDRlNzcxZWI4ZDBlODI2ZmFjMDIiLCJzaWQiOiJhZjg3NGY4YS02MjlhLTRiOGMtYTZmNC01ZmFhZmQ0ODg2MDEiLCJzdWIiOiI2ZDU4Yjg3Mi0xMzgwLTQ1OWMtOWFiMS"
				+ "1mMmZlZWY4MjA3YTIiLCJpc3ViIjoiNzI2NTExM2EtZDc3Ny00MTAxLThlMjItOGE2MWYwM2JjNjk4IiwiYXVkIjoiYTNkMGM2NGMtZDYyOS00NjQ2LWFlZGYtODgyODBiYWQ4MjcxIiwiaWF0IjoxNTkxMjgzMzQ3LCJhdXRoX3"
				+ "RpbWUiOjE1OTEyODMzNDcsImlzcyI6Imh0dHBzOi8vYm9zY2gtdGVzdC5jaWRhYXMuZGUiLCJqdGkiOiI5ZjliYzZhYy1kMjkyLTRkNDktOTA4My1kZGM5ZDIzYzhmNzQiLCJzY29wZXMiOlsiY2lkYWFzOnVzZXJpbmZvIiwiY2lkYWFzOndya"
				+ "XRlIiwiY2lkYWFzOnJlYWQiLCJvZmZsaW5lX2FjY2VzcyJdLCJyb2xlcyI6WyJVU0VSIiwiQk9TQ0hfQURNSU4iLCJCT1NDSF9TVVBFUl9BRE1JTiIsIkJPU0NIX1VTRVIiXSwiZ3JvdXBzIjpbeyJncm91cElkIjoiQ0lEQUFTX0FETUlOUyIsIn"
				+ "JvbGVzIjpbIkFETUlOIl19XSwiZXhwIjoxNTkxMzY5NzQ3fQ.i95T_W86rwnXdoiJB3KG8Ptx2AMxtc11LeVu83_YyRvKdqJ8-vb3jkTdbCZPrIKjCLuaLVeZ3SG1x-FGHkcZWUoA4k9BRI7J8aN1H04DbxG9Zikj_NSo1pKKMWZzFb7kfmRBd98rUqyq_5GePX02OB"
				+ "1ofBAalvKMhSa9QHD8s4E";
		Map<String, String> headers = new HashMap<>();
		headers.put("Authorization",headerValue);
		when(request.getHeader("Authorization")).thenReturn(null);
		BearerSecurityContextRepository bearerSecurityContextRepository = new BearerSecurityContextRepository();
		HttpRequestResponseHolder req = new HttpRequestResponseHolder(request,response);
		//when(req.getRequest()).thenReturn(request);
		assertNotNull(bearerSecurityContextRepository.loadContext(req));
		
		
	}
	
	@Test
	public void testLoadContextNegativeCase2() {
		String  headerValue = "bearer-eyJhbGciOiJSUzI1NiIsImtpZCI6IjAxZTM2YmFkLWQ3M2MtNGY1ZC05NDkxLTFjZjVjYTFhMDA3OSJ9.eyJhbXIiOlsiMiJdLCJ1YV9oYXNoIjoiNzY2NG"
				+ "ViZWZkMWM5NDRlNzcxZWI4ZDBlODI2ZmFjMDIiLCJzaWQiOiJhZjg3NGY4YS02MjlhLTRiOGMtYTZmNC01ZmFhZmQ0ODg2MDEiLCJzdWIiOiI2ZDU4Yjg3Mi0xMzgwLTQ1OWMtOWFiMS"
				+ "1mMmZlZWY4MjA3YTIiLCJpc3ViIjoiNzI2NTExM2EtZDc3Ny00MTAxLThlMjItOGE2MWYwM2JjNjk4IiwiYXVkIjoiYTNkMGM2NGMtZDYyOS00NjQ2LWFlZGYtODgyODBiYWQ4MjcxIiwiaWF0IjoxNTkxMjgzMzQ3LCJhdXRoX3"
				+ "RpbWUiOjE1OTEyODMzNDcsImlzcyI6Imh0dHBzOi8vYm9zY2gtdGVzdC5jaWRhYXMuZGUiLCJqdGkiOiI5ZjliYzZhYy1kMjkyLTRkNDktOTA4My1kZGM5ZDIzYzhmNzQiLCJzY29wZXMiOlsiY2lkYWFzOnVzZXJpbmZvIiwiY2lkYWFzOndya"
				+ "XRlIiwiY2lkYWFzOnJlYWQiLCJvZmZsaW5lX2FjY2VzcyJdLCJyb2xlcyI6WyJVU0VSIiwiQk9TQ0hfQURNSU4iLCJCT1NDSF9TVVBFUl9BRE1JTiIsIkJPU0NIX1VTRVIiXSwiZ3JvdXBzIjpbeyJncm91cElkIjoiQ0lEQUFTX0FETUlOUyIsIn"
				+ "JvbGVzIjpbIkFETUlOIl19XSwiZXhwIjoxNTkxMzY5NzQ3fQ.i95T_W86rwnXdoiJB3KG8Ptx2AMxtc11LeVu83_YyRvKdqJ8-vb3jkTdbCZPrIKjCLuaLVeZ3SG1x-FGHkcZWUoA4k9BRI7J8aN1H04DbxG9Zikj_NSo1pKKMWZzFb7kfmRBd98rUqyq_5GePX02OB"
				+ "1ofBAalvKMhSa9QHD8s4E";
		when(request.getHeader("Authorization")).thenReturn(headerValue);
		BearerSecurityContextRepository bearerSecurityContextRepository = new BearerSecurityContextRepository();
		HttpRequestResponseHolder req = new HttpRequestResponseHolder(request,response);
		//when(req.getRequest()).thenReturn(request);
		assertNotNull(bearerSecurityContextRepository.loadContext(req));
		
		
	}
	
	@Test
	public void testSaveContext() {
		BearerSecurityContextRepository bearerSecurityContextRepository = new BearerSecurityContextRepository();
		bearerSecurityContextRepository.saveContext(context, request, response);
		
		
	}
	
	
	@Test
	public void testContainContext() {
		BearerSecurityContextRepository bearerSecurityContextRepository = new BearerSecurityContextRepository();
		assertTrue(bearerSecurityContextRepository.containsContext(request));
		
	}

	@Test
	public void testUsingInvalidToken() {
		BearerSecurityContextRepository bearerSecurityContextRepository = new BearerSecurityContextRepository();
		assertNull(bearerSecurityContextRepository.creatAuthenticationUsingToken("abc.abc.abc"));
	}
	
	@Test
	public void testUsingNullValue() {
		BearerSecurityContextRepository bearerSecurityContextRepository = new BearerSecurityContextRepository();
		assertNull(bearerSecurityContextRepository.creatAuthenticationUsingToken(null));
	}
}
