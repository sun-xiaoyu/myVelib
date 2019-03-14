package myVelib;

public interface PlanningAlgo {
	public Answer handle(Request request, CurrentDistribution curDis) throws Exception;
}
