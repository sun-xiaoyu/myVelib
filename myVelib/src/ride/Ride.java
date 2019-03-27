package ride;

import bicycle.Bicycle;
import station.Station;

public abstract class Ride{
	protected User user;
	protected Station startStation, endStation;
	protected long startTime;
	protected long endTime;
	protected Bicycle bicycle;
	protected double fee;
	protected boolean ispaid;
	protected double lengthInMin;
	protected int timeCreditUsed;
	
	public boolean isIspaid() {
		return ispaid;
	}
	public int getTimeCreditUsed() {
		return timeCreditUsed;
	}
	public User getUser() {
		return user;
	}
	public Station getStartStation() {
		return startStation;
	}
	public Station getEndStation() {
		return endStation;
	}
	public long getStartTime() {
		return startTime;
	}
	public long getEndTime() {
		return endTime;
	}
	public Bicycle getBicycle() {
		return bicycle;
	}
	public double getFee() {
		return fee;
	}
	public boolean isPaid() {
		return ispaid;
	}
	public void setPaid() {
		ispaid = true;
	}
	public double getLengthInMin() {
		return lengthInMin;
	}
	

	
}
