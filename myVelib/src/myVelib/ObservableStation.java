package myVelib;

import java.util.ArrayList;

public class ObservableStation implements Observable{
	/**
	 *  when  a station is full, 
	 *  all related observers should receive notification
	 */
	private ArrayList<Observer> observers = new ArrayList<Observer>();
	private boolean changed;
	private Station station;
	
	public ObservableStation(Station station) {
		this.station = station;
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
	public void setAvailability() {
		this.changed = true;
		this.notifyObservers();
	}

}

