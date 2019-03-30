package card;

import bicycle.EBike;
import bicycle.MBike;
import ride.OngoingRide;
/**
 * visitor pattern to calculate cost of each riding for different card and duration
 * @author Zhihao Li
 *
 */
public interface CardVisitor {
	double visit(EBike ebike, OngoingRide ride);
	double visit(MBike mbike, OngoingRide ride);
}
