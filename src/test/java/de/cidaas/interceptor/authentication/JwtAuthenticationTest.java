package de.cidaas.interceptor.authentication;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import de.cidaas.jwt.JWT;
import de.cidaas.jwt.interfaces.DecodedJWT;

public class JwtAuthenticationTest {
	
	private String getTokenWithRolesAndScopes() {
		// Scopes --> cidaas:userinfo , cidaas:write, cidaas:read, offline_access
		// Roles --> USER, BOSCH_ADMIN, BOSCH_SUPER_ADMIN, BOSCH_USER
		// Groups --> CIDAAS_ADMINS with roles ADMIN
		return "eyJhbGciOiJSUzI1NiIsImtpZCI6IjAxZTM2YmFkLWQ3M2MtNGY1ZC05NDkxLTFjZjVjYTFhMDA3OSJ9.eyJhbXIiOlsiMiJdLCJ1YV9oYXNoIjoiNzY2NGViZWZkMWM5NDRlNzcxZWI4ZDBlODI2ZmFjMDIiLCJzaWQiOiJhZjg3NGY4YS02MjlhLTRiOGMtYTZmNC01ZmFhZmQ0ODg2MDEiLCJzdWIiOiI2ZDU4Yjg3Mi0xMzgwLTQ1OWMtOWFiMS1mMmZlZWY4MjA3YTIiLCJpc3ViIjoiNzI2NTExM2EtZDc3Ny00MTAxLThlMjItOGE2MWYwM2JjNjk4IiwiYXVkIjoiYTNkMGM2NGMtZDYyOS00NjQ2LWFlZGYtODgyODBiYWQ4MjcxIiwiaWF0IjoxNTkxMjgzMzQ3LCJhdXRoX3RpbWUiOjE1OTEyODMzNDcsImlzcyI6Imh0dHBzOi8vYm9zY2gtdGVzdC5jaWRhYXMuZGUiLCJqdGkiOiI5ZjliYzZhYy1kMjkyLTRkNDktOTA4My1kZGM5ZDIzYzhmNzQiLCJzY29wZXMiOlsiY2lkYWFzOnVzZXJpbmZvIiwiY2lkYWFzOndyaXRlIiwiY2lkYWFzOnJlYWQiLCJvZmZsaW5lX2FjY2VzcyJdLCJyb2xlcyI6WyJVU0VSIiwiQk9TQ0hfQURNSU4iLCJCT1NDSF9TVVBFUl9BRE1JTiIsIkJPU0NIX1VTRVIiXSwiZ3JvdXBzIjpbeyJncm91cElkIjoiQ0lEQUFTX0FETUlOUyIsInJvbGVzIjpbIkFETUlOIl19XSwiZXhwIjoxNTkxMzY5NzQ3fQ.i95T_W86rwnXdoiJB3KG8Ptx2AMxtc11LeVu83_YyRvKdqJ8-vb3jkTdbCZPrIKjCLuaLVeZ3SG1x-FGHkcZWUoA4k9BRI7J8aN1H04DbxG9Zikj_NSo1pKKMWZzFb7kfmRBd98rUqyq_5GePX02OB1ofBAalvKMhSa9QHD8s4E";
	}
	
	private String getTokenWithoutRoles() {
		// Scopes --> Testscope
		// Roles --> none
		return "eyJhbGciOiJSUzI1NiIsImtpZCI6ImZjOTY2MDI1LTU4MzctNDM0YS04YTBmLTkzOGJlMzBhMjRmZiJ9.eyJ1YV9oYXNoIjoiNzY2NGViZWZkMWM5NDRlNzcxZWI4ZDBlODI2ZmFjMDIiLCJzaWQiOiI4Y2E3OTYxNy1mZjhhLTRhMWQtYWJjOC0yNjM5YzI1OGJmMzAiLCJzdWIiOiJBTk9OWU1PVVMiLCJhdWQiOiIzMmJiOTYxYi02NmRmLTQ3OWUtOGYzYy01YjA1MDcyZWJmYjgiLCJpYXQiOjE1OTQ3OTUzNDMsImF1dGhfdGltZSI6MTU5NDc5NTM0MywiaXNzIjoiaHR0cHM6Ly9uaWdodGx5YnVpbGQuY2lkYWFzLmRlIiwianRpIjoiNWYwMTMyZjAtZTE2Zi00YmY5LWE1NzUtNDhlYWRmMzQwMWEwIiwic2NvcGVzIjpbIlRlc3RzY29wZSJdLCJleHAiOjE1OTQ4ODE3NDN9.s5K52lc0lSMZSXK2D2BHLViwWsN0vslfhmzsyvhpQn_yk9bftyzPjUo1HEnEhpREvPH029lX4F8eLLbSH2RtwXPFd3wxh-dqjdy81OquJfiOOs22EgiijRagtyl4-O41jqE0J02Xs7CtdxDAnDGZEo_a-qtu__nvMI_JZuUg_vo";
	}
	
	@Test
	public void testUsingValidTokenWithRolesAndScopes() {
//		JwtAuthentication jwtAuth = JwtAuthentication.creatAuthenticationUsingToken(getTokenWithRolesAndScopes());
		
//		DecodedJWT jwt = JWT.decode(getTokenWithRolesAndScopes());
//		
//		assertFalse(jwtAuth.isAuthenticated());
//		assertEquals(jwtAuth.getName(), jwt.getSubject());
//		assertEquals(jwtAuth.getPrincipal(), jwt.getSubject());
		//Compare objects...
//		assertArrayEquals(new String[] {"cidaas:userinfo", "cidaas:write", "cidaas:read", "offline_access", "ROLE_USER", "ROLE_BOSCH_ADMIN", "ROLE_BOSCH_SUPER_ADMIN", "ROLE_BOSCH_USER"}, jwtAuth.getAuthorities());
	}
}
