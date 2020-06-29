package de.cidaas.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ServerResponseTest {
	
	ServerResponse response = null;

	@Before
	public void setUp() throws Exception {
		
		response = new ServerResponse("Junit test");
	}

	
	
	@Test
	public void testServerResponseModel() {
		Assert.assertEquals("Junit test", response.getError());
		
	}

}
