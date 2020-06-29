package de.cidaas.jwk;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class BucketImplTest {
	
	
	BucketImpl bucketImpl;
	
	TimeUnit timeUnit;

	@Before
	public void setUp() throws Exception {
		timeUnit = mock(TimeUnit.class);
		bucketImpl = new BucketImpl(20, 21, timeUnit);
		
	}

	@Test
	public void testWillLeakIn() {
		when(timeUnit.toMillis(Mockito.anyLong())).thenReturn(55L);
		Assert.assertEquals(0, bucketImpl.willLeakIn());
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void testWillLeakInException() {
		bucketImpl = new BucketImpl(20, 21, timeUnit);
		Assert.assertEquals(0, bucketImpl.willLeakIn(21));
	}
	
	@Test
	public void testConsume() {
		bucketImpl = new BucketImpl(20, 19, timeUnit);
		when(timeUnit.toMillis(Mockito.anyLong())).thenReturn(55L);
		Assert.assertEquals(Boolean.TRUE, bucketImpl.consume());
	}
	
	
	
	

}
