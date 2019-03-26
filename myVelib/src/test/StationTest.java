package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import station.Station;

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
	
	@Test
	public void testStaions() {
		ArrayList<Station> stations = new ArrayList<Station>();
		for(int i = 0; i < 10; i++) {
			stations.add(new Station(10,1,1));
		}
		for(int i = 0; i < 10; i++) {
			for(int j = i + 1; j < 10; j++)
			assertFalse(stations.get(i).getPos() == stations.get(j).getPos());
		}
	}
}
