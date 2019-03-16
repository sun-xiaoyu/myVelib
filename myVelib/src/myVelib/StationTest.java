package myVelib;

import static org.junit.Assert.*;

import org.junit.Test;

public class StationTest {

	@Test
	public void testInitAllEBike() {
		Station s = new Station(10,1,1);
		assertEquals(10,s.getEBicycleNumber());
		assertEquals(0,s.getSpareSlotNum());
	}
	
	@Test
	public void testInitAllMBike() {
		Station s = new Station(10,1,0);
		assertEquals(10,s.getMBicycleNumber());
		assertEquals(0,s.getSpareSlotNum());
	}
	
	@Test
	public void testInitprint() {
		Station s = new Station(10,0.7,0.3);
		System.out.println(s);
	}
}
