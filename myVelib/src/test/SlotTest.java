package test;

import static org.junit.Assert.*;

import org.junit.Test;

import bicycle.Bicycle;
import bicycle.EBike;
import station.Slot;

class SlotTest {
	@Test
	public void testRestore() {
		Slot slot = new Slot();
		Bicycle bicycle = new EBike();
		slot.restore(bicycle);
		assertNotNull(slot.getBicycleInThisSlot());
	}
	
	@Test
	public void testRent() {
		Slot slot = new Slot();
		slot.addEleBicycle();
		slot.removeBicycle();
		assertNull(slot.getBicycleInThisSlot());
	}

	@Test
	void testToString() {
		Slot slot = new Slot(0.7,0.3);
		System.out.println(slot);
		EBike eb = new EBike();
		System.out.println(eb);
	}

}
