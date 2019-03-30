package bicycle;

import card.CardVisitor;
import ride.OngoingRide;
/**
 * Bike visitable of visitor pattern
 * @author Zhihao Li
 *
 */
public interface BikeVisitable {
	public double accept(CardVisitor visitor, OngoingRide ongoingRide);
}
