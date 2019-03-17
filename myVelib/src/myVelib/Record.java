package myVelib;

public class Record extends Ride{
	private long lenghInMin;
	private double distance;
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
	}
		
}
