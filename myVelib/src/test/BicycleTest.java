package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import bicycle.EBike;
import bicycle.MBike;

public class BicycleTest {

	@Test
	public void testId() {
		ArrayList<EBike> ebikes = new ArrayList<EBike>();
		ArrayList<MBike> mbikes = new ArrayList<MBike>();
		for(int i = 0; i < 10; i++) {
			ebikes.add(new EBike());
			mbikes.add(new MBike());
		}
		for(int j = 0; j < 10; j++) {
			assertEquals(ebikes.get(j).getBicycleId(), 2*j+1);
			assertEquals(mbikes.get(j).getBicycleId(), 2*j+2);
		}
	}
}
