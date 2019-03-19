package myVelib;

import java.util.ArrayList;

public class SetUpFullStationNotifier {
	/**
	 *  summarize functions of full station notification
	 */
	ArrayList<User> fullStationUsers = new ArrayList<User>();
	Station fullStation = null;
	
	public SetUpFullStationNotifier(Station fullStation) {
		super();
		this.fullStation = fullStation;
	}
	
	public void notifyFullStation(ObserverFullStation user, Station endStation) {
		ObservableStation observableFullStation = (ObservableStation)endStation;
		observableFullStation.registerObserver(user);
		ObservableStation observableFullStation = (ObservableStation)endStation;
		observableFullStation.registerObserver(user);
		
		
		
	}
	
	
	
	
}
