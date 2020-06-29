package de.cidaas.jwt.constants;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MessageConstantsTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		
		assertEquals("Invalid DER signature format.", MessageConstants.INVALID_DER_MESSAGE);
		assertEquals("Invalid JOSE signature format.", MessageConstants.INVALID_JOSE_MESSAGE);
		assertEquals("The given Private Key is null.", MessageConstants.PRIVATE_KEY_NULL_MESSAGE);
		assertEquals("The given Public Key is null.", MessageConstants.PUBLIC_KEY_NULL_MESSAGE);
		assertEquals("The Key Provider cannot be null.", MessageConstants.PROVIDER_KEY_NULL_MESSAGE);
		assertEquals("SameParameterValue", MessageConstants.SAME_PARAMETER_VALUE_MESSAGE);
		assertEquals("WeakerAccess", MessageConstants.WEAKER_ACCESS_MESSAGE);
	}

}
