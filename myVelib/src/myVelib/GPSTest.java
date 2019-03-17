package myVelib;

import static org.junit.Assert.*;

import org.junit.Test;

public class GPSTest {

	@Test
	public void testEqualsObject() {
		GPS gps1 = new GPS(2,5.10);
		GPS gps2 = new GPS(2,5.1);
		assertEquals(gps1,gps2);
	}

}
