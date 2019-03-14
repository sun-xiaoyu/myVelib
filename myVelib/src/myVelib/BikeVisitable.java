package myVelib;

public interface BikeVisitable {
	public double accept(CardVisitor visitor, OngoingRide ongoingRide);
}
