package myVelib;

public class IsFullObserver implements Observer {
	@Override
	public void update(Station station) {
		System.out.println(station + "is full,you can not return bike to it.");
	}
}
