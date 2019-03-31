package test;

import static org.junit.Assert.*;

import org.junit.Test;

import planning.Request;
import ride.User;
import station.GPS;

public class RequestTest {

	@Test
	public void testRequest() throws Exception {
		User Alice = new User();
		GPS startPos = new GPS();
		GPS endPos = new GPS();
		Request rq = new Request(Alice, startPos, endPos, "M", "MWD");
		assertEquals(rq.getBikeType(), "M");
		assertEquals(rq.getPolicy(), "MWD");
		assertNotSame(rq.getPolicy(), "FPTD");	
		assertEquals(rq.getUser(), Alice);
		
	}

}
