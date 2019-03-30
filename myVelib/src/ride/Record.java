package ride;
/**
 * a record can be easily noted by system
 * @author Zhihao Li
 *
 */
public class Record extends Ride{
	private double distance;
	private int timeCreditEarned;
	/**
	 * generate a record from a ride 
	 * @param ride information during a ride
	 */
	public Record(OngoingRide ride) {
		super();
		this.user = ride.user;
		this.startStation = ride.startStation;
		this.endStation = ride.endStation;
		this.startTime = ride.startTime;
		this.endTime = ride.endTime;
		this.bicycle = ride.bicycle;
		this.fee = ride.fee;
		this.ispaid = ride.ispaid;
		this.lengthInMin = ride.lengthInMin;
		if (this.endStation.isPlus()) this.timeCreditEarned = 5;
	}
	@Override
	public String toString() {
		return "Record [user=" + user + ", startStation=" + startStation.getStationId() + ", endStation="
				+ endStation.getStationId() + ", startTime=" + startTime + ", endTime=" + endTime + ", bicycle=" + bicycle + ", fee="
				+ fee + ", ispaid=" + ispaid + ", lengthInMin=" + lengthInMin + ", distance="
				+ distance + ", timeCreditEarned=" + timeCreditEarned + "]";
	}
	
	
}
