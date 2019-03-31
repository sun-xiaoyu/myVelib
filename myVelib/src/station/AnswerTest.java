package station;

import static org.junit.Assert.*;

import org.junit.Test;

import planning.Answer;

public class AnswerTest {

	@Test
	public void testAnswer() {
		Station s1 = new Station();
		Station s2 = new Station();
		double totalTime = 10;
		double totalDis = 20;
		Answer ans = new Answer(s1, s2, totalTime, totalDis);
		assertEquals(ans.getStartStation(), s1);
		assertEquals(ans.getEndStation(), s2);
		assertEquals(ans.getTotalTime(), totalTime, 1e-4);
		assertEquals(ans.getTotalDis(), totalDis, 1e-4);
		
		
	}

}
