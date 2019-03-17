package myVelib;

import org.junit.Test;

class GPSTest {
	@Test
	void testEqualsObject() {
		GPS gps1 = new GPS(2,5.10);
		GPS gps2 = new GPS(2,5.1);
		assertEquals(gps1,gps2);
	}

}
