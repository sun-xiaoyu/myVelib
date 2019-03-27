package test;

import static org.junit.Assert.*;

import org.junit.Test;

import bicycle.EBike;
import ride.OngoingRide;
import ride.User;
import station.Station;

public class OngoingRideTest {

	@Test
	public void testEndAt() throws Exception {
		User bob = new User("bob");
		Station startStation = new Station(false,2);
		EBike eBike = new EBike();
		OngoingRide ongoingRide = new OngoingRide(bob, startStation, eBike);
		Station endStation = new Station(false,2);
		try {
			for(int i = 6; i > 0; i--) {
				System.out.println("Thread: " + ", " + i);
				Thread.sleep(100);
			}
		}catch (InterruptedException e) {
			System.out.println("Thread " + " interrupted.");
		}
		ongoingRide.endAt(endStation);
		//System.out.println(ongoingRide);	
		assertEquals(ongoingRide.getUser(),bob);
		assertEquals(ongoingRide.getBicycle(),eBike);
		assertEquals(ongoingRide.isPaid(),false);
		assertEquals(ongoingRide.getStartStation(),startStation);
		assertEquals(ongoingRide.getEndStation(),endStation);
		assertEquals((ongoingRide.getEndTime() - ongoingRide.getStartTime()),600,50);
		assertEquals((ongoingRide.getLengthInMin()*60000),600,50);
	}
	@Test
	public void testEndAfter() throws Exception {
		User bob = new User("bob");
		Station startStation = new Station(false,2);
		EBike eBike = new EBike();
		OngoingRide ongoingRide = new OngoingRide(bob, startStation, eBike);
		Station endStation = new Station(false,  2);
		ongoingRide.endAfter(endStation,0.01);
		System.out.println(ongoingRide);
		assertEquals(ongoingRide.getUser(),bob);
		assertEquals(ongoingRide.getBicycle(),eBike);
		assertEquals(ongoingRide.isPaid(),false);
		assertEquals(ongoingRide.getStartStation(),startStation);
		assertEquals(ongoingRide.getEndStation(),endStation);
		assertEquals((ongoingRide.getEndTime() - ongoingRide.getStartTime()),600,50);
		assertEquals((ongoingRide.getLengthInMin()*60000),600,50);
	}
}