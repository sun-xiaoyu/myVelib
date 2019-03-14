package myVelib;

public class VmaxCardVisitor implements CardVisitor {

	@Override
	public double visit(EBike ebike, Ride ride) {
		return (ride.getLengthInMin()/60)*1;
	}

	@Override
	public double visit(MBike mbike, Ride ride) {
		return (ride.getLengthInMin()/60)*1;
	}
	
}
