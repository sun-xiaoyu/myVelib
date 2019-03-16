package myVelib;

import static org.junit.Assert.*;

import org.junit.Test;

public class MBikeTest {

	@Test
	public void test() {
		MBike mb = new MBike();
		assertEquals(Server.mecRidingSpeed, mb.getSpeed(), 1e-6);
	}

}
