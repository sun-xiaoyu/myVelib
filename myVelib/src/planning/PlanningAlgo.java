package planning;
/**
 *  different policies to calculate and return answers of requests
 * @author Zhihao Li
 *
 */
public interface PlanningAlgo {
	/**
	 * abstract method of calculating a request and return back the answer
	 * @param request a request sent by a user 
	 * @return an answer including necessary information
	 * @throws Exception several kinds of exceptions
	 */
	public Answer handle(Request request) throws Exception;
}
