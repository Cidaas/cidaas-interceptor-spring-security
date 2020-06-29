package de.cidaas.jwt.exceptions;

import org.junit.Before;
import org.junit.Test;

public class InvalidClaimExceptionTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test(expected = InvalidClaimException.class)
	public void test() {
		
		
		String s = "test";
		if(!"mock".equalsIgnoreCase(s)) {
			throw new InvalidClaimException("mock exception cidaas");
			
		}
	}

}
