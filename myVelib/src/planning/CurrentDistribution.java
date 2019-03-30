package planning;

import java.util.ArrayList;
import java.util.HashMap;

import station.Station;

/**
 * Singleton Pattern Used.
 * @author LIZHIHAO
 *
 */
public class CurrentDistribution {
	private HashMap<Integer, Station> allStation;
	private  ArrayList<Station> eAvaStationList;
	private ArrayList<Station> mAvaStationList;
	private ArrayList<Station> returnableStationList;
	private ArrayList<Station> rentableStationList;
	private static CurrentDistribution instance;
	
	private CurrentDistribution(Map map) {
		this.allStation = new HashMap<Integer, Station>();
		this.eAvaStationList = new ArrayList<Station>();
		this.mAvaStationList = new ArrayList<Station>();
		this.returnableStationList = new ArrayList<Station>();
		this.rentableStationList = new ArrayList<Station>();
		this.allStation = map.getStations();
		for(Station s : this.allStation.values()) {
			if(s.getEBicycleNumber() > 0) {
				this.eAvaStationList.add(s);
				this.rentableStationList.add(s);
				}
			if(s.getMBicycleNumber() > 0) {
				this.mAvaStationList.add(s);
				this.rentableStationList.add(s);
				}
			if(s.isFull() == false) {
				this.returnableStationList.add(s);
				}
		}
		
	}
	
	public static CurrentDistribution getInstance() {
		if (instance == null) {
			instance = new CurrentDistribution(Map.getInstance());	
		}
		else {
			for(Station s : instance.allStation.values()) {
				if(s.getEBicycleNumber() > 0) {
					instance.eAvaStationList.add(s);
					instance.rentableStationList.add(s);
					}
				if(s.getMBicycleNumber() > 0) {
					instance.mAvaStationList.add(s);
					instance.rentableStationList.add(s);
					}
				if(s.isFull() == false) {
					instance.returnableStationList.add(s);
					}
			}	
		}
		return instance;
	}

	public HashMap<Integer, Station> getAllStation() {
		return allStation;
	}

	public ArrayList<Station> geteAvaStationList() {
		return eAvaStationList;
	}

	public ArrayList<Station> getmAvaStationList() {
		return mAvaStationList;
	}

	public ArrayList<Station> getRetunableStationList() {
		return returnableStationList;
	}
	
	public ArrayList<Station> getReturnableStationList() {
		return returnableStationList;
	}

	public ArrayList<Station> getRentableStationList() {
		return rentableStationList;
	}

	public void deleAvastation(Station s) {
		this.eAvaStationList.remove(s);
	}
	
	public void delmAvaStation(Station s) {
		this.mAvaStationList.remove(s);
	}	
	
	public void delRetuenableStation(Station s) {
		this.returnableStationList.remove(s);
	}
}