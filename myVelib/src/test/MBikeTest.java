package test;

import static org.junit.Assert.*;

import org.junit.Test;

import bicycle.EBike;
import bicycle.MBike;
import ride.Server;

public class MBikeTest {

	@Test
	public void testSpeed() {
		MBike mb = new MBike();
		assertEquals(mb.getSpeed(),Server.mecRidingSpeed, 1e-2);
	}
	@Test
	public void testType() {
		MBike mb = new MBike();
		assertEquals(mb.getType(),'M');
	}
	@Test
	public void testRidingStatus() {
		MBike mb = new MBike();
		assertEquals(mb.isRidingStatus(), false);
		mb.setRidingStatus(true);
		assertEquals(mb.isRidingStatus(), true);
	}

	@Test
	public void testId() {
		EBike eb = new EBike();
		assertEquals(eb.getBicycleId(), 3);
	}
}
