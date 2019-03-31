package ride;

import java.util.Calendar;

import bicycle.Bicycle;
import card.CardVisitor;
import card.NoCardVisitor;
import card.VlibreCardVisitor;
import card.VmaxCard;
import card.VmaxCardVisitor;
import station.Station;
/**
 * generate a "half riding" with only starting information
 * @author Zhihao Li
 *
 */
public class OngoingRide extends Ride {
	/**
	 * generate a "half riding" with only starting information without given start time
	 * @param user riding user
	 * @param startStation start station
	 * @param bicycle riding bicycle 
	 */
	public OngoingRide(User user, Station startStation, Bicycle bicycle) {
		super();
		this.user = user;
		this.startStation = startStation;
		this.startTime = Calendar.getInstance().getTimeInMillis();
		this.bicycle = bicycle;
	}
	/**
	 * generate a "half riding" with only starting information with given start time
	 * @param user riding user
	 * @param startStation start station
	 * @param bicycle riding bicycle 
	 * @param startTime given start time
	 */
	public OngoingRide(User user, Station startStation, Bicycle bicycle, long startTime) {
		super();
		this.user = user;
		this.startStation = startStation;
		this.startTime = startTime;
		this.bicycle = bicycle;
	}
	/**
	 * override toString method of OngoingRide
	 */
	@Override
	public String toString() {
		return "OngoingRide [user=" + user + ", startStation=" + startStation + ", endStation=" + endStation
				+ ", startTime=" + startTime + ", endTime=" + endTime + ", bicycle=" + bicycle + ", fee=" + fee
				+ ", ispaid=" + ispaid + ", lengthInMin=" + lengthInMin + "]";
	}
	/**
	 * to finish a ride at a station with system time to be noted down
	 * @param endStation the end station to be noted down
	 */
	public void endAt(Station endStation) {
		this.endTime = Calendar.getInstance().getTimeInMillis();
		this.endStation = endStation;
		this.lengthInMin = (double)(this.endTime - this.startTime)/1000/60;
	}
	
	/**
	 * Let the ride end after a certain amount of time (durationInMin)
	 * @param endStation the end station to be noted down
	 * @param lengthInMin The length of time of the ride, in minutes.
	 */
	public void endAfter(Station endStation, double lengthInMin) {
		this.endTime = this.startTime + (long)lengthInMin*60*1000;
		this.endStation = endStation;
		this.lengthInMin = lengthInMin;
	}
	/**
	 * to get duration of a ride in order to calculate cost
	 * @return duration time to be charged
	 */
	public double getTimeNeedsToPay() {
		double duration = this.getLengthInMin();
		int credit = 0;
		if(user.isWithCard() == true) {
			credit = this.user.getCard().getTimeCredit();
		}
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
