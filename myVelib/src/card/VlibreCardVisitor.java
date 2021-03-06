package card;

import bicycle.EBike;
import bicycle.MBike;
import ride.OngoingRide;
/**
 * to calculate cost of a riding with vlibre card
 * @author Zhihao Li
 *
 */
public class VlibreCardVisitor implements CardVisitor {
	/**
	 * cost of electric bike for Vlibre card
	 */
	@Override
	public double visit(EBike ebike, OngoingRide ride) {
		double duration = ride.getTimeNeedsToPay();
		
//		The first hour is 1 euro per 60min, then 2 euro per 60 min.
		if (duration < 60.005) return duration / 60 * 1;
		return (duration-60)/60*2 + 1;
	}
	/**
	 * cost of mechanic bike for Vlibre card
	 */
	@Override
	public double visit(MBike mbike, OngoingRide ride) {
		double duration = ride.getTimeNeedsToPay();
		if (duration < 60.005) return 0;
		return (duration-60)/60*1 + 0;
	}

}
