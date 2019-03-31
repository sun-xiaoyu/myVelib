package planning;

import station.Station;
/**
 * an answer is calculated by our system, which contains start/end station,
 * total time and total distance of a riding 
 * @author Zhihao Li
 *
 */
public class Answer {
	private Station startStation, endStation;
	private double totalTime,totalDis;
	/**
	 * answer can only be generated after calculation to a request
	 * @param startStation start station
	 * @param endStation end station
	 * @param totalTime total riding and walking time 
	 * @param totalDis total riding and walking distance
	 */
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
		return "Answer [startStation=" + startStation.getStationId() + ", endStation=" + endStation.getStationId() + ", totalTime=" + totalTime
				+ ", totalDis=" + totalDis + "]";
	}
	
	
}
