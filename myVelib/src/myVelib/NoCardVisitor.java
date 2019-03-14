package myVelib;

public class NoCardVisitor implements CardVisitor {
	
	@Override
	public double visit(EBike ebike, Ride ride) {
		return (ride.getLengthInMin()/60 + 1) * 2;
	}

	@Override
	public double visit(MBike mbike, Ride ride) {
		return (ride.getLengthInMin()/60 + 1) * 1;
	}

}
