package myVelib;

public class Sol {
	private Station startStation, endStation;
	private double totalTime,totalDis;
	public Sol(Station startStation, Station endStation, double totalTime, double totalDis) {
		super();
		this.startStation = startStation;
		this.endStation = endStation;
		this.totalTime = totalTime;
		this.totalDis = totalDis;
	}
	public Station getStartStation() {
		return startStation;
	}
	public Station getEndStation() {
		return endStation;
	}
	public double getTotalTime() {
		return totalTime;
	}
	public double getTotalDis() {
		return totalDis;
	}
	
}
