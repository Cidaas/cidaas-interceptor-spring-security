package de.cidaas.jwt;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class ClockImplTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetDate() {
		
		ClockImpl c = new ClockImpl();
		Date d = new Date();
		assertEquals(c.getToday(),d);
	}
}
