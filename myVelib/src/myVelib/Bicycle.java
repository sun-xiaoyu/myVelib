package myVelib;

public abstract class Bicycle {
	private int bicycleId;
	protected float speed;
	private boolean ridingStatus;
	private static int idConstructor;
	
	public Bicycle() {
		super();
		this.bicycleId = ++idConstructor;
		this.ridingStatus = false;
	}
	
	public int getBicycleId() {
		return bicycleId;
	}
	public float getSpeed() {
		return speed;
	}
	public boolean isRidingStatus() {
		return ridingStatus;
	}
	public void setRidingStatus(boolean ridingStatus) {
		this.ridingStatus = ridingStatus;
	}

	public abstract double accept(CardVisitor visitor, OngoingRide ongoingRide);

}
