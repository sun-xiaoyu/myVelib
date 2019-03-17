package myVelib;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

public class FastPathToDestinationTest {

	@Test
	public void testHandle() {
		CurrentDistribution curDis = CurrentDistribution.getInstance();
		User bob = new User("bob");
		Random random = new Random();
		GPS startPos = new GPS(random.nextInt(41),random.nextInt(41));
		GPS endPos = new GPS(random.nextInt(41),random.nextInt(41));
		char bikeType = 'E';
		String policy = "FPTD";
		Request request = new Request(bob, startPos, endPos, bikeType, policy);
		Answer answer = FastPathToDestination.handle(request,curDis);
	}

}
