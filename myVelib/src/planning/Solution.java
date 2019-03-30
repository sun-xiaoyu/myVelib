package planning;

import ride.Server;
import station.Station;
/**
 * a solution is corresponding to a request with all necessary information
 * @author Zhihao Li
 *
 */
public class Solution {
	private Station startStation, endStation;
	private Request rq;
	private PlanningAlgo algo;
	private double totalTime,totalDis;
	/**
	 * solution constructor with algorithm initialized by minimal walking distance policy
	 * @param rq the request to be solved
	 */
	public Solution(Request rq) {
		super();
		this.rq = rq;
		switch(rq.getPolicy().toUpperCase()) {
		case "MWD":
			this.algo = new MinWalDis();
			break;
		case "FPTD":
			this.algo = new FastPathToDestination();
			break;
		case "APS":
			this.algo = new AvoidPlusStation();
			break;
		case "PPS":
			this.algo = new PreferPlusStation();
			break;
		case "POU":
			this.algo = new PreservationOfUniformity();
			break;
		default:
			this.algo = new MinWalDis();
			Server.error("policy illegal, and initialized to MinWalkDis policy");
		}
	}
	
	public Station getStartStation() {
		return startStation;
	}
	public void setStartStation(Station startStation) {
		this.startStation = startStation;
	}
	public Station getEndStation() {
		return endStation;
	}
	public void setEndStation(Station endStation) {
		this.endStation = endStation;
	}
	public Request getRq() {
		return rq;
	}
	public void setRq(Request rq) {
		this.rq = rq;
	}
	public PlanningAlgo getSolver() {
		return algo;
	}
	public void setSolver(PlanningAlgo solver) {
		this.algo = solver;
	}
	public double getTotalTime() {
		return totalTime;
	}
	public void setTotalTime(double totalTime) {
		this.totalTime = totalTime;
	}
	public double getTotalDis() {
		return totalDis;
	}
	public void setTotalDis(double totalDis) {
		this.totalDis = totalDis;
	}
	/**
	 * override toString method of solution
	 */
	@Override
	public String toString() {
		return "Solution [startStation=" + startStation.getStationId() + ", endStation=" + endStation.getStationId() + ", totalTime=" + totalTime
				+ ", totalDis=" + totalDis + "]" + rq;
	}
	/**
	 * 
	 */
	public void solve() {
		Answer ans;
		try {
			ans = this.algo.handle(this.rq);
			this.endStation = ans.getEndStation();
			this.startStation = ans.getStartStation();
			this.totalDis = ans.getTotalDis();
			this.totalTime = ans.getTotalTime();
			System.out.println(this);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	/**
	 * a solution can be accepted by user so that it would start to be announced 
	 * with offline information of end station,
	 * also record this acceptance into Server
	 */
	public void accept() {
		if(this.startStation != this.endStation) {
			Server server = Server.getInstance();
			server.getSolutions().put(this.rq.getUser(),this);
			if(!this.getEndStation().getReturnObservableStation().getObservers().contains(this.rq.getUser())) {
				this.getEndStation().getReturnObservableStation().registerObserver(this.rq.getUser());
			}
			Server.log("you've accepted this solution");
		}
		else {
			Server.error("you've accepted a solution where start station is the same as end station");
		}
	}
}
