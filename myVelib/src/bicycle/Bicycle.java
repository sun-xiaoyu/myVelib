package bicycle;

import card.CardVisitor;
import ride.OngoingRide;

/**
 * abstract class Bicycle contains 2 concrete classes: Ebike and Mbike.
 * @author Zhihao Li
 *
 */
public abstract class Bicycle {
	private int bicycleId;
	protected double speed;
	private boolean ridingStatus;
	private static int idConstructor;
	protected char type;
	
	/**
	 * to construct a concrete bike.
	 */
	public Bicycle(){
		super();
		this.bicycleId = ++idConstructor;
		this.ridingStatus = false;
	}
	
	public int getBicycleId() {
		return bicycleId;
	}
	public double getSpeed() {
		return speed;
	}
	public boolean isRidingStatus() {
		return ridingStatus;
	}
	public void setRidingStatus(boolean ridingStatus) {
		this.ridingStatus = ridingStatus;
	}
	public char getType() {
		return type;
	}

	/**
	 * to calculate total spend of an order
	 * @param visitor visitor pattern
	 * @param ongoingRide an order of a riding record.
	 * @return total spend of a riding
	 */
	public abstract double accept(CardVisitor visitor, OngoingRide ongoingRide);

	@Override
	public String toString() {
		return "Bicycle [bicycleId=" + bicycleId + ", ridingStatus=" + ridingStatus + ", type="
				+ type + "]";
	}
	
	
}
