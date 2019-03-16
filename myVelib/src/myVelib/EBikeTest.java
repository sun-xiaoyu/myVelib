package myVelib;

import static org.junit.Assert.*;

import org.junit.Test;

public class EBikeTest {

	@Test
	public void testSpeed() {
		EBike eb = new EBike();
		assertEquals(Server.eleRidingSpeed, eb.getSpeed(), 1e-6);
	}

}
