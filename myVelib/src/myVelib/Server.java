package myVelib;

import java.util.ArrayList;

public class Server {
	private ArrayList <Ride> rideList;
	private Map map;
	private double occupation = 0.3;
	private double eleRate = 0.3;
	private int numEleBicyle;
	private int numMecBicyle;
	private CurrentDistribution curDis;
	public static final double walkingSpeed = (double)4/60;
	public static final double mecRidingSpeed = (double)15/60;
	public static final double eleRidingSpeed = (double)20/60;
	
	public Server(Map map) {
		this.map = map;
		for(Station s: map.getStationList()) {
			
		}
	}
	
	public static void restore(User u, Bike b, Station s) {
		if (s.notFull()&&~s.offline()){
			if (b == u.getRide().getBike()) {// this is to prevent a bug, which a user might use two cards to alternate renting bikes at return everyone in an hour.
				s.addBike(b); // this method also handles the update of status of Bike b.
				long timeEnd = Calendar.getTimeInMillis();
				Ride r = u.getRide();
				r.endAt(timeEnd); //Attention of units of time! this is millimeter
				float charge = r.charge(); // this method takes into account of user's card i.e. his time_credit and adds new time credit
				u.pay(charge);
				u.addTimeCredit(r); //Time_credit +=5 if ... and ...
				Record = new Record(r);
				this.addRecord(r);
				this.removeRide(r);	
			}
			else this.error("The same bike should be returned!");
		}else this.error("The station is full!");
	}
	
	public static void rent(User u, Bike b, Station s) {
		if (s.hasBike(b))&&(~s.offline()){
			if (this.plannedRideList.contains(u)) {
				s.releaseBike(b); // this method also handles the update of status of Bike b.
				long timeStart = Calendar.getTimeInMillis(); //Attention of units of time! this is millimeter
				Ride r = u.getRide();
				r.startAt(timeStart,b,s);
			}else {
				s.releaseBike(b); // this method also handles the update of status of Bike b.
				long timeStart = Calendar.getTimeInMillis(); //Attention of units of time! this is millimeter
				Ride r = new Ride(u,b,s,timeStart);
				u.setRide(r); //ride object can be found again by user object. i.e. when user identified himself at the return station terminal.
				this.addRide(r);
			}
			
		}else this.error("The station is full!");
	}
	
	public Solution handle(Request rq) {
		Solution solution = new Solution(rq);
		return solution.getSolver().handle(rq)
	}
}

