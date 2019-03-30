package bicycle;

import card.CardVisitor;
import ride.OngoingRide;
/**
 * electric bicycle
 * @author Zhihao Li
 *
 */
public class EBike extends Bicycle implements BikeVisitable{

	/**
	 * Set electric bicycle's speed in km/min
	 */
	public EBike() {
		super();
		this.speed = (double)0.33;
		this.type = 'E';
	}
	/**
	 * calculate electric bike cost in visitor pattern
	 */
	public double accept(CardVisitor visitor, OngoingRide ongoingRide) {
		return visitor.visit(this, ongoingRide);
	}
}
