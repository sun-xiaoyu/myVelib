package myVelib;

public class ObserverFullStation extends User implements Observer {
	@Override
	public void update(Station station) {
		System.out.println(station + "is full,you can not return bike to it.");
	}
}
