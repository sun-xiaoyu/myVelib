package card;

import bicycle.EBike;
import bicycle.MBike;
import ride.OngoingRide;

public class NoCardVisitor implements CardVisitor {
	
	@Override
	public double visit(EBike ebike, OngoingRide ride) {
		return (ride.getLengthInMin()/60 + 1) * 2;
	}

	@Override
	public double visit(MBike mbike, OngoingRide ride) {
		return (ride.getLengthInMin()/60 + 1) * 1;
	}

}
