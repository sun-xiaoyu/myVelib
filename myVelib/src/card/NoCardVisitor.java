package card;

import bicycle.EBike;
import bicycle.MBike;
import ride.OngoingRide;
/**
 * to calculate cost of a riding with no card
 * @author Zhihao Li
 *
 */
public class NoCardVisitor implements CardVisitor {
	/**
	 * cost of electric bike 
	 */
	@Override
	public double visit(EBike ebike, OngoingRide ride) {
		return (ride.getLengthInMin()/60) * 2;
	}
	/**
	 * cost of electric bike 
	 */
	@Override
	public double visit(MBike mbike, OngoingRide ride) {
		return (ride.getLengthInMin()/60) * 1;
	}

}
