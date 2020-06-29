package de.cidaas.jwt.exceptions;

import org.junit.Before;
import org.junit.Test;

import de.cidaas.jwt.algorithms.Algorithm;
import de.cidaas.jwt.interfaces.DecodedJWT;

public class SignatureGenerationExceptionTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test(expected = SignatureGenerationException.class)
	public void test() {
		
		String s = "test";
		if(!"mock".equalsIgnoreCase(s)) {
			throw new SignatureGenerationException(new Algorithm("test", "test") {
			
			@Override
			public void verify(DecodedJWT jwt) throws SignatureVerificationException {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public byte[] sign(byte[] contentBytes) throws SignatureGenerationException {
				// TODO Auto-generated method stub
				return null;
			}
		}, new Throwable());
			
		}
	
	}

}
