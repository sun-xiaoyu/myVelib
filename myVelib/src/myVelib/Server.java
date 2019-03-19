package myVelib;

import java.util.ArrayList;
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
	 * @throws Exception 
	 */
	public void rent(User user, Station station, char bicycleType) throws Exception{
		if (!station.isOffline()){
			boolean successful = false;
			for (Slot slot: station.getSlots()) {
				if (slot.isOccupied()) {
					Bicycle bicycle = slot.getBicycleInThisSlot();
					if (bicycle instanceof MBike && bicycleType == 'M' || bicycle instanceof EBike && bicycleType == 'E') {
						successful = true;
						station.getRentObservableStation().removeObserver(user);
						station.rentBicycle(bicycle);
						
						OngoingRide ride = new OngoingRide(user, station, bicycle);
						user.setRiding(true);
						instance.ongoingRides.put(user,ride);
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
	 * @throws Exception 
	 */
	public void rent(User user, Station station) throws Exception {
		if (!station.isOffline()){
			boolean successful = false;
			for (Slot slot: station.getSlots()) {
				if (slot.isOccupied()) {
					Bicycle bicycle = slot.getBicycleInThisSlot();
					successful = true;
					station.rentBicycle(bicycle);
					OngoingRide ride = new OngoingRide(user, station, bicycle);
					user.setRiding(true);
					instance.ongoingRides.put(user,ride);
					log("Rented with success.");
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
	 * @throws Exception 
	 * @throws IllegalArgumentException 
	 */
	public void restore(User user, Station station) throws IllegalArgumentException, Exception {
		if (!station.isOffline()){
			if (!station.judgeFull()) {
				for (Slot slot: station.getSlots()) {
					if (!slot.isOccupied()) {
						OngoingRide ride = instance.ongoingRides.get(user);
						ride.endAt(station);
						Bicycle bicycle = ride.getBicycle();
						station.returnBicycle(bicycle, slot);
						user.setRiding(false);
						user.payFor(ride);
						if (station.isPlus()) user.getCard().addCredit(5);
						Record record = new Record(ride);
						instance.ongoingRides.remove(user);
						instance.updateStatistic(record);
						log("Returned with success, user paid " + String.format("%.2f",record.getFee())+ " EUR.");
						log(record.toString());
						station.getReturnObservableStation().removeObserver(user);;
						if(station.isFull()) {
							station.getReturnObservableStation().notifyObservers();
						}
						break;
					}
				}
			} else error("Return failed. The station is full!");
		}else error("Return failed. The station is offline!");
	}
	
	public void restoreAfter(User user, Station station, double LengthInMin) throws IllegalArgumentException, Exception {
		if (!station.isOffline()){
			if (!station.judgeFull()) {
				for (Slot slot: station.getSlots()) {
					if (!slot.isOccupied()) {
						OngoingRide ride = instance.ongoingRides.get(user);
						ride.endAfter(station,LengthInMin);
						Bicycle bicycle = ride.getBicycle();
						station.returnBicycle(bicycle, slot);
						user.setRiding(false);
						user.payFor(ride);
						if (station.isPlus() && user.isWithCard()) user.getCard().addCredit(5);
						Record record = new Record(ride);
						instance.ongoingRides.remove(user);
						instance.updateStatistic(record);
						log("Returned with success, user paid " + String.format("%.2f",record.getFee())+ " EUR.");
						log(record.toString());
						station.getReturnObservableStation().removeObserver(user);;
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
	private void updateStatistic(Record record) {
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

