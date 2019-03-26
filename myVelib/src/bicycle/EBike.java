package bicycle;

import card.CardVisitor;
import ride.OngoingRide;

public class EBike extends Bicycle implements BikeVisitable{

	/**
	 * Set the speed : in km/min
	 */
	public EBike() {
		super();
		this.speed = (double)0.33;
		this.type = 'E';
	}
	
	public double accept(CardVisitor visitor, OngoingRide ongoingRide) {
		return visitor.visit(this, ongoingRide);
	}
}
