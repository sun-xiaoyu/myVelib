package myVelib;

import static org.junit.Assert.*;

import org.junit.Test;

public class MapTest {

	@Test
	public void testInitIntInt() {
		fail("Not yet implemented");
	}

	@Test
	/**
	 * This test has helped us to identify the bug that the slot ArrayList are not initialized in the constructor of Station.
	 */
	void testInit() {
		Map map = Map.getInstance();
		map.init();		
		System.out.println(map);
//		fail("Not yet implemented");
	}
	
	public static void main(String [] args) {
		MapTest maptest = new MapTest();
		maptest.testInit();
	}

	@Test
	public void testGetInstance() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetStationList() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetStationNum() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetTotalBicycleNum() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetEleTotalBicycleNum() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetMecTotalBicycleNum() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddStation() {
		fail("Not yet implemented");
	}

	@Test
	public void testDelStation() {
		fail("Not yet implemented");
	}

	@Test
	public void testToString() {
		fail("Not yet implemented");
	}

}
