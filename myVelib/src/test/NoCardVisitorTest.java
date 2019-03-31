package test;

import static org.junit.Assert.*;

import org.junit.Test;

import bicycle.EBike;
import bicycle.MBike;
import card.NoCardVisitor;
import ride.OngoingRide;
import ride.User;
import station.Station;

public class NoCardVisitorTest {

	@Test
	public void testVisitEBikeOngoingRide() {
		User Alice = new User("Alice");
		Station s1 =  new Station();
		EBike eb = new EBike();
		OngoingRide ogr = new OngoingRide(Alice, s1, eb);
		ogr.endAfter(s1, 120);
		NoCardVisitor ncv = new NoCardVisitor();
		assertEquals(ncv.visit(new EBike(), ogr),4, 0.1);
	}

	@Test
	public void testVisitMBikeOngoingRide() {
		User Alice = new User("Alice");
		Station s1 =  new Station();
		MBike mb = new MBike();
		OngoingRide ogr = new OngoingRide(Alice, s1, mb);
		ogr.endAfter(s1, 120);
		NoCardVisitor ncv = new NoCardVisitor();
		assertEquals(ncv.visit(new MBike(), ogr), 2, 0.1);
	}

}
