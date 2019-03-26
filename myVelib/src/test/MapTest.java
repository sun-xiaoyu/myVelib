package test;

import org.junit.Test;

import planning.Map;

public class MapTest {

	@Test
	/**
	 * This test has helped us to identify the bug that the slot ArrayList are not initialized in the constructor of Station.
	 */
	public void testInit() {
		Map map = Map.getInstance();
		map.init();		
		System.out.println(map);
	}
	
	public static void main(String [] args) {
		MapTest maptest = new MapTest();
		maptest.testInit();
	}

}
