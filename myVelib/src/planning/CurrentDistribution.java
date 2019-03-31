package planning;

import java.util.ArrayList;
import java.util.HashMap;

import station.Station;

/**
 * this class hold station distribution in current time, in order that we can easily get station availability
 * @author Zhihao Li
 *
 */
public class CurrentDistribution {
	private HashMap<Integer, Station> allStation;
	private  ArrayList<Station> eAvaStationList;
	private ArrayList<Station> mAvaStationList;
	private ArrayList<Station> returnableStationList;
	private ArrayList<Station> rentableStationList;
	private static CurrentDistribution instance;
	/**
	 * generate a current distribution from map created before this
	 * @param map regroups all stations
	 */
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
	/**
	 * singleton pattern to ensure that there's only one unique instance of current distribution
	 * @return the only one current distribution
	 */
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
	/**
	 * to set a station not returnable
	 * @param s the station to set not returnable
	 */
	public void delRetuenableStation(Station s) {
		this.returnableStationList.remove(s);
	}
	public void reset() {
		instance = null;		
	}
}