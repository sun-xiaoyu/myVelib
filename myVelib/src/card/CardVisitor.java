package card;

import bicycle.EBike;
import bicycle.MBike;
import ride.OngoingRide;

public interface CardVisitor {
	double visit(EBike ebike, OngoingRide ride);
	double visit(MBike mbike, OngoingRide ride);
}
