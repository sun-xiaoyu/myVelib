package test;

import static org.junit.Assert.*;

import org.junit.Test;

import bicycle.EBike;
import bicycle.MBike;
import card.VlibreCardVisitor;
import ride.OngoingRide;
import ride.User;
import station.Station;

public class VlibreCardVisitorTest {
	/**
	 * test out an error that ongoingride may get in a null point when referring to credit time. 
	 */
	@Test
	public void testVisitEBikeOngoingRide1() {
		User Alice = new User("Alice");
		Station s1 =  new Station();
		EBike eb = new EBike();
		OngoingRide ogr = new OngoingRide(Alice, s1, eb);
		ogr.endAfter(s1, 30);
		VlibreCardVisitor ncv = new VlibreCardVisitor();
		assertEquals(ncv.visit(new EBike(), ogr), 0.5, 0.1);
	}
	
	@Test
	public void testVisitEBikeOngoingRide2() {
		User Alice = new User("Alice");
		Station s1 =  new Station();
		EBike eb = new EBike();
		OngoingRide ogr = new OngoingRide(Alice, s1, eb);
		ogr.endAfter(s1, 90);
		VlibreCardVisitor ncv = new VlibreCardVisitor();
		assertEquals(ncv.visit(new EBike(), ogr), 2, 0.1);
	}
	
	@Test
	public void testVisitMBikeOngoingRide1() {
		User Alice = new User("Alice");
		Station s1 =  new Station();
		MBike mb = new MBike();
		OngoingRide ogr = new OngoingRide(Alice, s1, mb);
		ogr.endAfter(s1, 50);
		VlibreCardVisitor ncv = new VlibreCardVisitor();
		assertEquals(ncv.visit(new MBike(), ogr), 0, 0.1);
	}	
	@Test
	public void testVisitMBikeOngoingRide2() {
		User Alice = new User("Alice");
		Station s1 =  new Station();
		MBike mb = new MBike();
		OngoingRide ogr = new OngoingRide(Alice, s1, mb);
		ogr.endAfter(s1, 90);
		VlibreCardVisitor ncv = new VlibreCardVisitor();
		assertEquals(ncv.visit(new MBike(), ogr), 0.5, 0.1);
	}
}
