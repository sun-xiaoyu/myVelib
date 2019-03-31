package test;

import static org.junit.Assert.*;

import org.junit.Test;

import card.Card;
import ride.User;

public class CardTest {

	@Test
	public void testCard() {
		User Alice = new User("Alice");
		Card card = new Card(Alice);
		assertSame(card.getUser(), Alice);
	}

	@Test
	public void testAddCredit() {
		User Alice = new User("Alice");
		Card card = new Card(Alice);
		card.addCredit(13);
		assertEquals(card.getTimeCredit(), 13);
		card.addCredit(12);
		assertEquals(card.getTimeCredit(), 25);
	}

}
