package de.cidaas.jwt;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.interfaces.RSAKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import de.cidaas.jwt.JWTCreator.Builder;
import de.cidaas.jwt.algorithms.Algorithm;
import de.cidaas.jwt.interfaces.ECDSAKeyProvider;
import de.cidaas.jwt.interfaces.RSAKeyProvider;

public class JWTCreatorTest {
	
	Builder builder ;
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
	public void testSign() {
		Algorithm algo = mock(Algorithm.class);
		builder = JWTCreator.init();
		String expected = "eyJ0eXAiOiJKV1QiLCJhbGciOm51bGx9.e30.null";
		String actual = builder.sign(algo);
		assertEquals(expected,actual);
		
	}
	
	
	
	

}
