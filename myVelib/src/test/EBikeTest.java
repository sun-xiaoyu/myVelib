package test;

import static org.junit.Assert.*;

import org.junit.Test;

import bicycle.EBike;
import ride.Server;

public class EBikeTest {


	@Test
	public void testSpeed() {
		EBike eb = new EBike();
		assertEquals(eb.getSpeed(),Server.eleRidingSpeed, 1e-2);
	}
	@Test
	public void testType() {
		EBike eb = new EBike();
		assertEquals(eb.getType(),'E');
	}
	
	@Test
	public void testRidingStatus() {
		EBike eb = new EBike();
		assertFalse(eb.isRidingStatus());
		eb.setRidingStatus(true);
		assertTrue(eb.isRidingStatus());
	}

	@Test
	public void testId() {
		EBike eb = new EBike();
		assertEquals(eb.getBicycleId(), 3);
	}
}
