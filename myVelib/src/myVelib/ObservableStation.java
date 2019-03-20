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
		if(!observers.contains(observer))
			observers.add(observer);}
	@Override
	public void removeObserver(Observer observer) {
		if(observers.contains(observer)) {
			observers.remove(observer);}
		else {
			System.out.println("observers is trying to delete an observer which doesn't exist in it!");
		}
	}
	@Override
	public void notifyObservers() {
		if (this.changed) {
			for (Observer ob : observers)
				if (((User)ob).isRiding()) {
					ob.update(this.station);
				}
			this.changed = false;
		}
	}
	// setInterest() invoke notifyObserver()
	public void setAvailability() {
		this.changed = true;
		this.notifyObservers();
	}

}

