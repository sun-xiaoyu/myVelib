package test;

import static org.junit.Assert.*;

import org.junit.Test;

import planning.Map;
import ride.Server;
import ride.User;

public class ServerTest {

	@Test
	public void test() throws Exception {
		assertNotNull(Server.getInstance());
		Map map = Map.getInstance();
		map.init();
		User Alice = new User();
		Server.getInstance().rent(Alice, Map.getInstance().getStations().get(1));
		assertTrue(Alice.isRiding());
	}

}
