package myVelib;

public class OffLineObserver implements Observer {
	@Override
	public void update(Station station) {
		System.out.println(station + "is offline,you can not rent or return bike to it.");
	}
}

