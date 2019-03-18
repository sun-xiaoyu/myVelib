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
	
	public OngoingRide(User user, Station startStation, Bicycle bicycle, long startTime) {
		super();
		this.user = user;
		this.startStation = startStation;
		this.startTime = startTime;
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
		this.endTime = this.startTime + (long)lengthInMin*60*1000;
		this.endStation = endStation;
		this.lengthInMin = lengthInMin;
	}
	
	public double getTimeNeedsToPay() {
		double duration = this.getLengthInMin();
		int credit = this.user.getCard().getTimeCredit();
		if (duration >60 && credit != 0) {
			if (duration - credit > 60) {
				// The user will use all his time credit.
				duration = duration - credit;
				this.setTimeCreditUsed(credit);
			}else {
				/*
				 * The user will use some of his time credit so that he don't have to pay. 
				 * The user use his time credit to make his ride last exactly 60 min.
				 */
				duration = 60;
				this.setTimeCreditUsed((int)duration - 60);
			}
		}
		return duration;
	}
	
	public void setTimeCreditUsed(int timeCreditUsed) {
		this.timeCreditUsed = timeCreditUsed;
	}
	
	/**
	 * Calculate the cost of the ride.
	 * @return The total cost of the ride, in EUR
	 */
	public double charge() {
		CardVisitor visitor;
		if (this.user.isWithCard() ==  false) visitor = new NoCardVisitor();
		else if (this.user.getCard() instanceof VmaxCard) visitor = new VmaxCardVisitor();
		else visitor = new VlibreCardVisitor();
		this.fee = this.bicycle.accept(visitor,this);
		return this.fee;
	}
	
}
