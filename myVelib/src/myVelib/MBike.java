package myVelib;

public class MBike extends Bicycle implements BikeVisitable {
	
	/**
	 * Set the speed : in km/min
	 */
	public MBike() {
		super();
		this.speed = 15/60;
	}
	
	@Override
	public double accept(CardVisitor visitor, OngoingRide ongoingRide) {
		return visitor.visit(this, ongoingRide);
	}
}
