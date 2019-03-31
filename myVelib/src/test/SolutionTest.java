package test;

import static org.junit.Assert.*;

import org.junit.Test;

import planning.Map;
import planning.Request;
import planning.Solution;
import ride.User;
import station.GPS;

public class SolutionTest {

	@Test
	public void solveTest() throws Exception {
		Map map = Map.getInstance();
		map.init();
		User Alice = new User();
		GPS startPos = new GPS();
		GPS endPos = new GPS();
		Request rq = new Request(Alice, startPos, endPos, "M", "MWD");
		Solution solution = new Solution(rq);
		solution.solve();
		assertNotNull(solution.getStartStation());
		assertNotNull(solution.getEndStation());
		assertNotNull(solution.getTotalDis());
		assertNotNull(solution.getTotalTime());
	}

}
