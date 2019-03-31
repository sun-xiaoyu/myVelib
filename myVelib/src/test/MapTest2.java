package test;

import static org.junit.Assert.*;

import org.junit.Test;

import planning.Map;

public class MapTest2 {

	@Test
	public void testInit() throws Exception {
		Map map = Map.getInstance();
		map.init(10, 100,100, 40);	
		System.out.println(map);
		assertEquals(map.getStationNum(), 10);
		assertEquals(map.getTotalSlotNum(), 100);
		assertEquals(map.getTotalBicycleNum(), 100);
		assertEquals(map.getEleTotalBicycleNum(), 30, 10);
		assertEquals(map.getMecTotalBicycleNum(), 70, 10);
	}
	
}
