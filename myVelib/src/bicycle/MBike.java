package bicycle;

import card.CardVisitor;
import ride.OngoingRide;
/**
 * mechanic bike
 * @author Lenovo
 *
 */
public class MBike extends Bicycle implements BikeVisitable {
	
	/**
	 * Set mechanic bike's speed in km/min
	 */
	public MBike() {
		super();
		this.speed = (double)15/60;
		this.type = 'M';
	}
	/**
	 * calculate mechanic bike cost in visitor pattern
	 */
	@Override
	public double accept(CardVisitor visitor, OngoingRide ongoingRide) {
		return visitor.visit(this, ongoingRide);
	}
}
