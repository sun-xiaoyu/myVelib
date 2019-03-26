package test;

import static org.junit.Assert.*;

import org.junit.Test;

import bicycle.MBike;
import ride.Server;

public class MBikeTest {

	@Test
	public void testSpeed() {
		MBike mb = new MBike();
		assertEquals(Server.mecRidingSpeed, mb.getSpeed(), 1e-6);
	}

}
