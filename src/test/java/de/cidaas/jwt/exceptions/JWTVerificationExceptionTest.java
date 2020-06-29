package de.cidaas.jwt.exceptions;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class JWTVerificationExceptionTest {

	@Before
	public void setUp() throws Exception {
	}

	
	
	@Test(expected = JWTVerificationException.class)
	public void test2() {
		
		String s = "test";
		if(!"mock".equalsIgnoreCase(s)) {
			throw new JWTVerificationException("mock exception cidaas",new Throwable());
			
		}
		
	}

}
