package card;

import bicycle.EBike;
import bicycle.MBike;
import ride.OngoingRide;

public class VmaxCardVisitor implements CardVisitor {

	@Override
	public double visit(EBike ebike, OngoingRide ride) {
		double duration = ride.getTimeNeedsToPay();
		if (duration < 60.005) return 0;
		return (duration-60)/60*1 + 0;
	}

	@Override
	public double visit(MBike mbike, OngoingRide ride) {
		double duration = ride.getTimeNeedsToPay();
		if (duration < 60.005) return 0;
		return (duration-60)/60*1 + 0;
	}
	
}
