package de.cidaas.jwt.exceptions;

import org.junit.Before;
import org.junit.Test;

public class AlgorithmMismatchExceptionTest {

	@Before
	public void setUp() throws Exception {
	}
	
	
	

	@Test(expected = AlgorithmMismatchException.class)
	public void testException() {
		String s = "test";
		if(!"mock".equalsIgnoreCase(s)) {
			throw new AlgorithmMismatchException("mock exception cidaas");
			
		}
		
		//Mockito.doNothing();
	}
	

}
