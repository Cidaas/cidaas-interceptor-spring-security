package de.cidaas.jwt.exceptions;

import org.junit.Before;
import org.junit.Test;

public class JWTCreationExceptionTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test(expected = JWTCreationException.class)
	public void test() {
		
		String s = "test";
		if(!"mock".equalsIgnoreCase(s)) {
			throw new JWTCreationException("mock exception cidaas",new Throwable());
			
		}
	
	}

}
