package card;

import bicycle.EBike;
import bicycle.MBike;
import ride.OngoingRide;
/**
 * to calculate cost of a riding with Vmax card
 * @author Zhihao Li
 *
 */
public class VmaxCardVisitor implements CardVisitor {
	/**
	 * cost of electric bike for Vmax card
	 */
	@Override
	public double visit(EBike ebike, OngoingRide ride) {
		double duration = ride.getTimeNeedsToPay();
		if (duration < 60.005) return 0;
		return (duration-60)/60*1 + 0;
	}
	/**
	 * cost of mechanic bike for Vmax card
	 */
	@Override
	public double visit(MBike mbike, OngoingRide ride) {
		double duration = ride.getTimeNeedsToPay();
		if (duration < 60.005) return 0;
		return (duration-60)/60*1 + 0;
	}
	
}
