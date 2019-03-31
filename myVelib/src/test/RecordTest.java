package test;

import static org.junit.Assert.*;

import org.junit.Test;

import bicycle.EBike;
import ride.OngoingRide;
import ride.Record;
import ride.User;
import station.Station;

public class RecordTest {

	@Test
	public void test() throws Exception {
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
		Record record = new Record(ongoingRide);
		assertEquals(record.getTimeCreditEarned(), 0);
	}

}
