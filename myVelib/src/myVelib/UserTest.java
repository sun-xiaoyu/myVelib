package myVelib;

import static org.junit.Assert.*;

import org.junit.Test;

public class UserTest {

	@Test
	public void testAddCard() {
		Server server = Server.getInstance();
		User Bob = new User();
		server.addUser(Bob);
		Bob.addCard(new VmaxCard(Bob));
		assertTrue(Bob.isWithCard());
	}

}
