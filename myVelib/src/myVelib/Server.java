package myVelib;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class Server {
	private ArrayList <Record> records;
	private ArrayList <User> users;
	private HashMap<User, OngoingRide> ongoingRides;
	private double occupation = 0.3;
	private double eleRate = 0.3;
	private int numEleBicyle;
	private int numMecBicyle;
	public static final double walkingSpeed = (double)4/60;
	public static final double mecRidingSpeed = (double)15/60;
	public static final double eleRidingSpeed = (double)20/60;
	private static Server instance;

	private Server() {
		this.records = new ArrayList <Record>();
		this.ongoingRides = new HashMap<User, OngoingRide>();
		this.users = new ArrayList <User>();
	}
	public static Server getInstance() {
		if (instance == null) {
			instance = new Server();
		}
		return instance;
	}
	
	/**
	 * A user try to rent a bicycle of a certain type.
	 * @param user The user who rents the bicycle.
	 * @param station The station where the bicycle is rented.
	 * @param bicycleType The type of bike the user wanted.
	 */
	public static void rent(User user, Station station, char bicycleType) {
		if (!station.isOffline()){
			boolean successful = false;
			for (Slot slot: station.getSlots()) {
				if (slot.isOccupied()) {
					Bicycle bicycle = slot.getBicycleInThisSlot();
					if (bicycle instanceof MBike && bicycleType == 'm' || bicycle instanceof EBike && bicycleType == 'e') {
						successful = true;
						OngoingRide ride = new OngoingRide(user, station, bicycle);
						slot.removeBicycle();
						user.setRiding(true);
						bicycle.setRidingStatus(true);
						instance.ongoingRides.put(user,ride);
						slot.removeBicycle();
						break;
					}
				}
			}
			if (!successful) System.out.println("Rent failed. The station does not have the bicycle of required type!");
		}else System.out.println("Rent failed. The station is offline!");
	}
	
	/**
	 * A user tries to rent a bike at station, where the type of bike does not metter.
	 * @param user The user who rents the bicycle.
	 * @param station
	 */
	public static void rent(User user, Station station) {
		if (!station.isOffline()){
			boolean successful = false;
			for (Slot slot: station.getSlots()) {
				if (slot.isOccupied()) {
					Bicycle bicycle = slot.getBicycleInThisSlot();
					successful = true;
					OngoingRide ride = new OngoingRide(user, station, bicycle);
					slot.removeBicycle();
					user.setRiding(true);
					bicycle.setRidingStatus(true);
					instance.ongoingRides.put(user,ride);
					break;
				}
			}
			if (!successful) error("Rent failed. The station does not have any bike!");
		}else error("Rent failed. The station is offline!");
	}

	/**
	 * We suppose that by default the user will always return the bicycle that he just rented. The return time is the system time.
	 * That is why restore does not take Bicycle as an parameter.
	 * This might lead to prevent a bug, which a user might use two cards to alternate renting bikes at return everyone in an hour.??	 *
	 * @param user The user who returns the bicycle.
	 * @param station The station where the bicycle is returned.
	 */
	public static void restore(User user, Station station) {
		if (!station.isOffline()){
			if (!station.judgeFull()) {
				for (Slot slot: station.getSlots()) {
					if (!slot.isOccupied()) {
						OngoingRide ride = instance.ongoingRides.get(user);
						ride.endAt(station);
						Bicycle bicycle = ride.getBicycle();
						slot.restore(bicycle);
						user.setRiding(false);
						bicycle.setRidingStatus(false);
						user.payFor(ride);
						if (station.isPlus()) user.getCard().addCredit(5);
						Record record = new Record(ride);
						instance.ongoingRides.remove(user);
						instance.update(record);
						log("Returned with success, user paid" + record.getFee()+ "EUR.");
						break;
					}
				}
			} else error("Return failed. The station is full!");
		}else error("Return failed. The station is offline!");
	}
	
	/**
	 * This method is used to maintain the user balance and station balance using the information stored in record.
	 * @param record
	 */
	private void update(Record record) {
		// TODO Auto-generated method stub
		
	}
	
	public Solution handle(Request rq) {
		Solution solution = new Solution(rq);
		solution.solve();
		return solution;
	}
	
	public static void error(String str) {
		System.out.println(str);
	}
	
	public static void log(String str) {
		System.out.println(str);
	}
	
	public void addUser(User user) {
		instance.users.add(user);
	}
	
	public static void addPlannedRide(Solution s1) {
		// TODO Auto-generated method stub
		
	}
}

