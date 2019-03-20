package myVelib;

import java.util.ArrayList;

public class ObservableOffLine implements Observable{
	/**
	 *  when a station is set offline, all related observers should receive notification
	 */
	private ArrayList<Observer> observers = new ArrayList<Observer>();
	private boolean changed;
	private Station station;
	public ObservableOffLine(Station station) {
		this.station = station;
		this.changed = false;
	}
	@Override
	public void registerObserver(Observer observer) {
		observers.add(observer);}
	@Override
	public void removeObserver(Observer observer) {
		observers.remove(observer);}
	@Override
	public void notifyObservers() {
		if (this.changed) {
			for (Observer ob : observers)
			ob.update(this.station);
			this.changed = false;
		}
	}
	// setInterest() invoke notifyObserver()
	public void setInterest(float i) {
		this.changed = true;
		this.notifyObservers();
	}

}
