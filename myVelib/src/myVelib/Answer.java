package myVelib;

public class Answer {
	private Station startStation, endStation;
	private double totalTime,totalDis;
	
	public Answer(Station startStation, Station endStation, double totalTime, double totalDis) {
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
	
	@Override
	public String toString() {
		return "Answer [startStation=" + startStation + ", endStation=" + endStation + ", totalTime=" + totalTime
				+ ", totalDis=" + totalDis + "]";
	}
	
}
