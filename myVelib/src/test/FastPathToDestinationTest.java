package test;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

import planning.Answer;
import planning.FastPathToDestination;
import planning.Request;
import ride.User;
import station.GPS;

public class FastPathToDestinationTest {

	@Test
	public void testHandle() throws Exception {
		User bob = new User("bob");
		Random random = new Random();
		GPS startPos = new GPS(random.nextInt(41),random.nextInt(41));
		GPS endPos = new GPS(random.nextInt(41),random.nextInt(41));
		String bikeType = "E";
		String policy = "FPTD";
		Request request = new Request(bob, startPos, endPos, bikeType, policy);
		FastPathToDestination FPTD = new FastPathToDestination();
		Answer answer = FPTD.handle(request);
		System.out.println(answer);
	}

}
