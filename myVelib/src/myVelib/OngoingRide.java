package myVelib;

import java.util.Calendar;

public class OngoingRide extends Ride {
	
	public OngoingRide(User user, Station startStation, Bicycle bicycle) {
		super();
		this.user = user;
		this.startStation = startStation;
		this.startTime = Calendar.getInstance().getTimeInMillis();
		this.bicycle = bicycle;
	}
	
	
	
	@Override
	public String toString() {
		return "OngoingRide [user=" + user + ", startStation=" + startStation + ", endStation=" + endStation
				+ ", startTime=" + startTime + ", endTime=" + endTime + ", bicycle=" + bicycle + ", fee=" + fee
				+ ", ispaid=" + ispaid + ", lengthInMin=" + lengthInMin + "]";
	}

	public void endAt(Station endStation) {
		this.endTime = Calendar.getInstance().getTimeInMillis();
		this.endStation = endStation;
		this.lengthInMin = (double)(this.endTime - this.startTime)/1000/60;
	}
	
	/**
	 * Let the ride end after a certain amount of time (durationInMin)
	 * @param endStation 
	 * @param durationInMin The length of time of the ride, in minutes.
	 */
	public void endAfter(Station endStation, double lengthInMin) {
		System.out.println(this.startTime);
		this.endTime = this.startTime + (long)(lengthInMin*60*1000);
		System.out.println(this.endTime);
		this.endStation = endStation;
		this.lengthInMin = lengthInMin;
	}

	/**
	 * Calculate the cost of the ride.
	 * @return The total cost of the ride, in EUR
	 */
	public double charge() {
		//Visitor pattern?
		
		//if (this.getUser().card instanceof Vmax) {}
		//TODO
		//return accept(this.getUser().getCard());
		CardVisitor visitor;
		if (this.user.isWithCard() ==  false) visitor = new NoCardVisitor();
		else if (this.user.getCard() instanceof VmaxCard) visitor = new VmaxCardVisitor();
		else visitor = new VlibreCardVisitor();
		this.fee = this.bicycle.accept(visitor,this);
		return this.fee;
	}
	
}
