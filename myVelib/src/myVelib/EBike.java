package myVelib;

public class EBike extends Bicycle implements BikeVisitable{

	/**
	 * Set the speed : in km/min
	 */
	public EBike() {
		super();
		this.speed = 20/60;
	}
	
	public double accept(CardVisitor visitor, OngoingRide ongoingRide) {
		return visitor.visit(this, ongoingRide);
	}
}
