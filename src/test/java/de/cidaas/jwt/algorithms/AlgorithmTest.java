package de.cidaas.jwt.algorithms;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.interfaces.RSAKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import de.cidaas.jwt.exceptions.AlgorithmMismatchException;
import de.cidaas.jwt.exceptions.InvalidClaimException;
import de.cidaas.jwt.exceptions.JWTCreationException;
import de.cidaas.jwt.exceptions.JWTVerificationException;
import de.cidaas.jwt.interfaces.ECDSAKeyProvider;
import de.cidaas.jwt.interfaces.RSAKeyProvider;

public class AlgorithmTest {
	
	
	
	
	RSAKeyProvider keyProvider;
	RSAPublicKey rsaPublicKey;
	RSAPrivateKey rsaPrivateKey;
	RSAKey rsaKey;
	ECDSAKeyProvider ecdsaKeyProvider;
	ECPublicKey ecPublicKey;
	ECPrivateKey ecPrivateKey;

	@Before
	public void setUp() throws Exception {
		
		keyProvider = mock(RSAKeyProvider.class);
		rsaPublicKey = mock(RSAPublicKey.class);
		rsaPrivateKey = mock(RSAPrivateKey.class);
		rsaKey = mock(RSAKey.class);
		ecdsaKeyProvider = mock(ECDSAKeyProvider.class);
		ecPublicKey = mock(ECPublicKey.class);
		ecPrivateKey = mock(ECPrivateKey.class);
	}
	
	
	@Test
	public void testRSA256() {
		
		Algorithm actual = Algorithm.RSA256(keyProvider);
		assertTrue(actual instanceof RSAAlgorithm);
		
		
	}
	
	@Test
	public void testRSA256Type2() {
		Algorithm actual = Algorithm.RSA256(rsaPublicKey, rsaPrivateKey);
		assertTrue(actual instanceof RSAAlgorithm);
	}
	
	@Test
	public void testRSA256Depricated() {
		@SuppressWarnings("deprecation")
		Algorithm actual = Algorithm.RSA256(rsaPublicKey);
		assertTrue(actual instanceof RSAAlgorithm);
	}
	
	
	
	@Test
	public void testRSA384() {
		Algorithm actual = Algorithm.RSA384(keyProvider);
		assertTrue(actual instanceof RSAAlgorithm);
	}
	
	
	@Test
	public void testRSA384Type2() {
		Algorithm actual = Algorithm.RSA384(rsaPublicKey, rsaPrivateKey);
		assertTrue(actual instanceof RSAAlgorithm);
	}
	

	@Test
	public void testRSA384Depricated() {
		@SuppressWarnings("deprecation")
		Algorithm actual = Algorithm.RSA384(rsaPublicKey);
		assertTrue(actual instanceof RSAAlgorithm);
	}
	
	@Test
	public void testRSA512() {
		Algorithm actual = Algorithm.RSA512(keyProvider);
		assertTrue(actual instanceof RSAAlgorithm);
	}
	
	
	@Test
	public void testRSA512Type2() {
		Algorithm actual = Algorithm.RSA512(rsaPublicKey, rsaPrivateKey);
		assertTrue(actual instanceof RSAAlgorithm);
	}
	

	@Test
	public void testRSA512Depricated() {
		@SuppressWarnings("deprecation")
		Algorithm actual = Algorithm.RSA512(rsaPublicKey);
		assertTrue(actual instanceof RSAAlgorithm);
	}
	
	
	@Test
	public void testHMAC256() {
		
		Algorithm actual = Algorithm.HMAC256("mockedSecret");
		assertTrue(actual instanceof HMACAlgorithm);
	}
	@Test
	public void testHMAC384() {
		
		Algorithm actual = Algorithm.HMAC384("mockedSecret");
		assertTrue(actual instanceof HMACAlgorithm);
	}
	@Test
	public void testHMAC512() {
		
		Algorithm actual = Algorithm.HMAC512("mockedSecret");
		assertTrue(actual instanceof HMACAlgorithm);
	}
	
	
	@Test
	public void testHMAC256Type2() {
		
		Algorithm actual = Algorithm.HMAC256("mockedSecret".getBytes());
		assertTrue(actual instanceof HMACAlgorithm);
	}
	@Test
	public void testHMAC384Type2() {
		
		Algorithm actual = Algorithm.HMAC384("mockedSecret".getBytes());
		assertTrue(actual instanceof HMACAlgorithm);
	}
	@Test
	public void testHMAC512Type2() {
		
		Algorithm actual = Algorithm.HMAC512("mockedSecret".getBytes());
		assertTrue(actual instanceof HMACAlgorithm);
	}
	
	
	@Test
	public void testECDSA256() {
		
		Algorithm actual = Algorithm.ECDSA256(ecdsaKeyProvider);
		assertTrue(actual instanceof ECDSAAlgorithm);
	}
	
	@Test
	public void testECDSA256Type2() {
		
		Algorithm actual = Algorithm.ECDSA256(ecPublicKey,ecPrivateKey);
		assertTrue(actual instanceof ECDSAAlgorithm);
	}
	

	@Test
	public void testECDSA256Type2Depricated() {
		
		Algorithm actual = Algorithm.ECDSA256(ecPublicKey);
		assertTrue(actual instanceof ECDSAAlgorithm);
	}
	

	@Test
	public void testECDSA384() {
		
		Algorithm actual = Algorithm.ECDSA384(ecdsaKeyProvider);
		assertTrue(actual instanceof ECDSAAlgorithm);
	}
	
	@Test
	public void testECDSA384Type2() {
		
		Algorithm actual = Algorithm.ECDSA384(ecPublicKey,ecPrivateKey);
		assertTrue(actual instanceof ECDSAAlgorithm);
	}
	

	@Test
	public void testECDSA384Type2Depricated() {
		
		Algorithm actual = Algorithm.ECDSA384(ecPublicKey);
		assertTrue(actual instanceof ECDSAAlgorithm);
	}
	

	@Test
	public void testECDSA512() {
		
		Algorithm actual = Algorithm.ECDSA512(ecdsaKeyProvider);
		assertTrue(actual instanceof ECDSAAlgorithm);
	}
	
	@Test
	public void testECDSA512Type2() {
		
		Algorithm actual = Algorithm.ECDSA512(ecPublicKey,ecPrivateKey);
		assertTrue(actual instanceof ECDSAAlgorithm);
	}
	

	@Test
	public void testECDSA512Type2Depricated() {
		
		Algorithm actual = Algorithm.ECDSA512(ecPublicKey);
		assertTrue(actual instanceof ECDSAAlgorithm);
	}
	
	

	


	

}
