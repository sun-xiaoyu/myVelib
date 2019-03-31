package test;

import java.lang.reflect.Method;
import static org.junit.Assert.*;

import org.junit.Test;

import bicycle.Bicycle;
import clui.CLUI;
import planning.Map;
import ride.Server;

public class CLUITest {

	@Test
	public void testSetup() throws Exception
	{
		CLUI clui = new CLUI();
		Method declaredMethod = clui.getClass().getDeclaredMethod("parseAndDo", String.class);
		declaredMethod.setAccessible(true);
		declaredMethod.invoke(CLUI.class, "setup vlibre1");
		assertEquals(Map.getInstance().getStationNum(), 10);
		assertEquals(Map.getInstance().getStations().get(3).getSlotNum(), 10);
		int bikeNum = 0;
		for(int key: Map.getInstance().getStations().keySet()) {
			bikeNum += Map.getInstance().getStations().get(key).getBicycleNumber();
		}
		assertEquals(bikeNum, 70, 10);
		
		declaredMethod.invoke(CLUI.class, "addUser Alice Vlibre vlibre1");
		assertEquals(Server.getInstance().getUsers().size(), 1);
		declaredMethod.invoke(CLUI.class, "addUser Bob Vlibre vlibre1");
		assertEquals(Server.getInstance().getUsers().size(), 2);
		
		declaredMethod.invoke(CLUI.class, "offline vlibre1 2");
		assertTrue(Map.getInstance().getStations().get(2).isOffline());
		declaredMethod.invoke(CLUI.class, "online vlibre1 2");
		assertFalse(Map.getInstance().getStations().get(2).isOffline());
		
		declaredMethod.invoke(CLUI.class, "rentbike 2 3");
		assertFalse(Map.getInstance().getStations().get(3).getSlots().get(0).isOccupied());
		assertTrue(Server.getInstance().getUsers().get(2).isRiding());
		
		declaredMethod.invoke(CLUI.class, "returnbike 2 5 30");
		assertFalse(Server.getInstance().getUsers().get(2).isRiding());
		
		
		
		
		
		
		declaredMethod.setAccessible(false);
	}
}
