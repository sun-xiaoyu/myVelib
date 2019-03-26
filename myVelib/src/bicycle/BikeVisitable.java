package bicycle;

import card.CardVisitor;
import ride.OngoingRide;

public interface BikeVisitable {
	public double accept(CardVisitor visitor, OngoingRide ongoingRide);
}
