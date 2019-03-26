package test;

import static org.junit.Assert.*;

import org.junit.Test;

import planning.CurrentDistribution;
import planning.Map;

public class CurrentDistributionTest {
	
	@Test
	public void testGetInstance() {
	
	Map map = Map.getInstance();
	map.init();
	CurrentDistribution curDis = CurrentDistribution.getInstance();
	System.out.println(curDis.getAllStation());
	}
	
	@Test
	public void testGetAllStation() {
	}
/**
	@Test
	public void testGeteAvaStationList() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetmAvaStationList() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetRetunableStationList() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetReturnableStationList() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetRentableStationList() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleAvastation() {
		fail("Not yet implemented");
	}

	@Test
	public void testDelmAvaStation() {
		fail("Not yet implemented");
	}

	@Test
	public void testDelRetuenableStation() {
		fail("Not yet implemented");
	}
**/
}
