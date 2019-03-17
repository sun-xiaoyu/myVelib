package myVelib;

import org.junit.Test;

public class MapTest {

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

}
