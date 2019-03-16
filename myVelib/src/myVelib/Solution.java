package myVelib;

public class Solution {
	private Station startStation, endStation;
	private Request rq;
	private PlanningAlgo algo;
	private double totalTime,totalDis;
	public Solution(Request rq) {
		super();
		this.rq = rq;
		this.algo = new MinWalDis();
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


	public void solve() {
		Answer ans;
		try {
			ans = this.algo.handle(this.rq);
			this.endStation = ans.getEndStation();
			this.startStation = ans.getStartStation();
			this.totalDis = ans.getTotalDis();
			this.totalTime = ans.getTotalTime();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
}
