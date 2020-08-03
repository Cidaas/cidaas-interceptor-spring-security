package de.cidaas.jwt;

import static org.junit.Assert.assertNotNull;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import de.cidaas.jwt.algorithms.Algorithm;
import de.cidaas.jwt.interfaces.DecodedJWT;

public class JWTTest {
	JWT jwt;
	
	
	String  token = "eyJhbGciOiJSUzI1NiIsImtpZCI6IjAxZTM2YmFkLWQ3M2MtNGY1ZC05NDkxLTFjZjVjYTFhMDA3OSJ9.eyJhbXIiOlsiMiJdLCJ1YV9oYXNoIjoiNzY2NG"
			+ "ViZWZkMWM5NDRlNzcxZWI4ZDBlODI2ZmFjMDIiLCJzaWQiOiJhZjg3NGY4YS02MjlhLTRiOGMtYTZmNC01ZmFhZmQ0ODg2MDEiLCJzdWIiOiI2ZDU4Yjg3Mi0xMzgwLTQ1OWMtOWFiMS"
			+ "1mMmZlZWY4MjA3YTIiLCJpc3ViIjoiNzI2NTExM2EtZDc3Ny00MTAxLThlMjItOGE2MWYwM2JjNjk4IiwiYXVkIjoiYTNkMGM2NGMtZDYyOS00NjQ2LWFlZGYtODgyODBiYWQ4MjcxIiwiaWF0IjoxNTkxMjgzMzQ3LCJhdXRoX3"
			+ "RpbWUiOjE1OTEyODMzNDcsImlzcyI6Imh0dHBzOi8vYm9zY2gtdGVzdC5jaWRhYXMuZGUiLCJqdGkiOiI5ZjliYzZhYy1kMjkyLTRkNDktOTA4My1kZGM5ZDIzYzhmNzQiLCJzY29wZXMiOlsiY2lkYWFzOnVzZXJpbmZvIiwiY2lkYWFzOndya"
			+ "XRlIiwiY2lkYWFzOnJlYWQiLCJvZmZsaW5lX2FjY2VzcyJdLCJyb2xlcyI6WyJVU0VSIiwiQk9TQ0hfQURNSU4iLCJCT1NDSF9TVVBFUl9BRE1JTiIsIkJPU0NIX1VTRVIiXSwiZ3JvdXBzIjpbeyJncm91cElkIjoiQ0lEQUFTX0FETUlOUyIsIn"
			+ "JvbGVzIjpbIkFETUlOIl19XSwiZXhwIjoxNTkxMzY5NzQ3fQ.i95T_W86rwnXdoiJB3KG8Ptx2AMxtc11LeVu83_YyRvKdqJ8-vb3jkTdbCZPrIKjCLuaLVeZ3SG1x-FGHkcZWUoA4k9BRI7J8aN1H04DbxG9Zikj_NSo1pKKMWZzFb7kfmRBd98rUqyq_5GePX02OB"
			+ "1ofBAalvKMhSa9QHD8s4E";

	@Before
	public void setUp() throws Exception {
		jwt = new JWT();
	}

	
	
	
	@Test
	public void testDecodeJWT() {
		assertNotNull(jwt.decodeJwt(token));;
		
	}
	
	
	@Test
	public void testDecode() {
		assertNotNull(jwt.decode(token));;
	}
	
	
	@Test
	public void testVerification() {
		Algorithm algorithm = Mockito.mock(Algorithm.class);
		assertNotNull(jwt.require(algorithm));
		
	}
	
	@Test
	public void testCreator() {
		assertNotNull(jwt.create());
		
	}
	
	
	@Test
	public void testSettersGettersDecoder() {
		DecodedJWT decodeJwt = jwt.decodeJwt(token);
		Assert.assertEquals("RS256",decodeJwt.getAlgorithm());//RS256
		Assert.assertNull(decodeJwt.getType());//null
		Assert.assertNull(decodeJwt.getContentType());//null
		Assert.assertEquals("01e36bad-d73c-4f5d-9491-1cf5ca1a0079",decodeJwt.getKeyId());//
		Assert.assertNotNull(decodeJwt.getHeaderClaim(""));//
		Assert.assertEquals("https://bosch-test.cidaas.de",decodeJwt.getIssuer());//
		Assert.assertEquals("6d58b872-1380-459c-9ab1-f2feef8207a2",decodeJwt.getSubject());//
		Assert.assertEquals("a3d0c64c-d629-4646-aedf-88280bad8271",decodeJwt.getAudience().get(0));//[]
		Assert.assertEquals(1591369747000L,decodeJwt.getExpiresAt().getTime());//
		Assert.assertNull(decodeJwt.getNotBefore());//null
		Assert.assertEquals(1591283347000L,decodeJwt.getIssuedAt().getTime());//
		Assert.assertNotNull(decodeJwt.getId());//null
		Assert.assertNotNull(decodeJwt.getClaim(""));//
		Assert.assertNotNull(decodeJwt.getClaims());//
		Assert.assertNotNull(decodeJwt.getHeader());//eyJhbGciOiJSUzI1NiIsImtpZCI6IjAxZTM2YmFkLWQ3M2MtNGY1ZC05NDkxLTFjZjVjYTFhMDA3OSJ9
		Assert.assertEquals("eyJhbXIiOlsiMiJdLCJ1YV9oYXNoIjoiNzY2NGViZWZkMWM5NDRlNzcxZWI4ZDBlODI2ZmFjMDIiLCJzaWQ"
				+ "iOiJhZjg3NGY4YS02MjlhLTRiOGMtYTZmNC01ZmFhZmQ0ODg2MDEiLCJzdWIiOiI2ZDU4Yjg3Mi0xMzgwLT"
				+ "Q1OWMtOWFiMS1mMmZlZWY4MjA3YTIiLCJpc3ViIjoiNzI2NTExM2EtZDc3Ny00MTAxLThlMjItOGE2MWYwM2JjNjk4IiwiYXVkIjoiYTNkMGM2NGMtZDYyOS00NjQ2LWFlZGYtODgyOD"
				+ "BiYWQ4MjcxIiwiaWF0IjoxNTkxMjgzMzQ3LCJhdXRoX3RpbWUiOjE1OTEyODMzNDcsImlzcyI6Imh0dHBzOi8vYm9zY2gtdGVzdC5jaWRhYXMuZGUiLCJqdGkiOiI5ZjliYzZhYy1kMjkyLTR"
				+ "kNDktOTA4My1kZGM5ZDIzYzhmNzQiLCJzY29wZXMiOlsiY2lkYWFzOnVzZXJpbmZvIiwiY2lkYWFzOndyaXRlIiwiY2lkYWFzOnJlYWQiLCJvZmZsaW5lX2FjY2VzcyJdLCJyb2xlcyI6WyJVU0VS"
				+ "IiwiQk9TQ0hfQURNSU4iLCJCT1NDSF9TVVBFUl9BRE1JTiIsIkJPU0NIX1VTRVIiXSwiZ3JvdX"
				+ "BzIjpbeyJncm91cElkIjoiQ0lEQUFTX0FETUlOUyIsInJvbGVzIjpbIkFETUlOIl19XSwiZXhwIjoxNTkxMzY5NzQ3fQ",decodeJwt.getPayload());//
		Assert.assertEquals("i95T_W86rwnXdoiJB3KG8Ptx2AMxtc11LeVu83_YyRvKdqJ8-vb3jkTdbCZPrIKj"
				+ "CLuaLVeZ3SG1x-FGHkcZWUoA4k9BRI7J8aN1H04DbxG9Zikj_NSo1pKKMWZzFb7kfmRBd98rUqyq_5GePX"
				+ "02OB1ofBAalvKMhSa9QHD8s4E",
				decodeJwt.getSignature());//
		Assert.assertEquals(token, decodeJwt.getTokenAsString());
	}
	
	

}