package myVelib;

public interface CardVisitor {
	double visit(EBike ebike, Ride ride);
	double visit(MBike mbike, Ride ride);
}
