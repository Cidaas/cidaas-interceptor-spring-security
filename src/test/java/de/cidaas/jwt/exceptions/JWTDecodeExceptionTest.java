package de.cidaas.jwt.exceptions;

import org.junit.Before;
import org.junit.Test;

public class JWTDecodeExceptionTest {

	@Before
	public void setUp() throws Exception {
	}

	
	@Test(expected = JWTDecodeException.class)
	public void test2() {
		
		String s = "test";
		if(!"mock".equalsIgnoreCase(s)) {
			throw new JWTDecodeException("mock exception cidaas",new Throwable());
			
		}
	
	}

}
