package test;

import static org.junit.Assert.*;

import org.junit.Test;

import card.VmaxCard;
import ride.Server;
import testSenario.User;

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
