package test;

import static org.junit.Assert.*;

import org.junit.Test;

import bicycle.EBike;
import ride.Server;

public class EBikeTest {

	@Test
	public void testSpeed() {
		EBike eb = new EBike();
		assertEquals(Server.eleRidingSpeed, eb.getSpeed(), 1e-6);
	}

}
