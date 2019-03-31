package test;

import org.junit.Test;
import static org.junit.Assert.*;
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
		assertEquals(map.getStationNum(), 10);
		assertEquals(map.getTotalSlotNum(), 100);
		assertEquals(map.getTotalBicycleNum(), 70, 10);
		assertEquals(map.getEleTotalBicycleNum(), 21, 5);
		assertEquals(map.getMecTotalBicycleNum(), 49, 10);
		
	}
	

}
