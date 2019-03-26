package bicycle;

import card.CardVisitor;
import ride.OngoingRide;

public class MBike extends Bicycle implements BikeVisitable {
	
	/**
	 * Set the speed : in km/min
	 */
	public MBike() {
		super();
		this.speed = (double)15/60;
		this.type = 'M';
	}
	
	@Override
	public double accept(CardVisitor visitor, OngoingRide ongoingRide) {
		return visitor.visit(this, ongoingRide);
	}
}