package de.cidaas.jwt.exceptions;

import org.junit.Before;
import org.junit.Test;

public class TokenExpiredExceptionTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test(expected = TokenExpiredException.class)
	public void test() {
		
		String s = "test";
		if(!"mock".equalsIgnoreCase(s)) {
			throw new TokenExpiredException("mock exception cidaas");
			
		}
	
	}

}
