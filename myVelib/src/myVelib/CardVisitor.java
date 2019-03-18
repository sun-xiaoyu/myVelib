package myVelib;

public interface CardVisitor {
	double visit(EBike ebike, OngoingRide ride);
	double visit(MBike mbike, OngoingRide ride);
}
