package myVelib;

import java.util.ArrayList;

/**
 * Singleton Pattern Used.
 * @author LIZHIHAO
 *
 */
public class CurrentDistribution {
	private ArrayList<Station> allStation;
	private ArrayList<Station> eAvaStationList;
	private ArrayList<Station> mAvaStationList;
	private ArrayList<Station> returnableStationList;
	private ArrayList<Station> rentableStationList;
	private static CurrentDistribution instance = null;
	
	private CurrentDistribution(Map map) {
		this.allStation = map.getStationList();
		
		for(Station s : this.allStation) {
			if(s.geteBicycleNumber() > 0) {
				this.eAvaStationList.add(s);
				this.rentableStationList.add(s);
				}
			if(s.getmBicycleNumber() > 0) {
				this.mAvaStationList.add(s);
				this.rentableStationList.add(s);
				}
			if(s.isFull() == false) {
				this.eAvaStationList.add(s);
				}
		}
	}
	
	public static CurrentDistribution getInstance() {
		if (instance == null) {
			instance = new CurrentDistribution(Map.getInstance());
		}
		return instance;
	}

	public ArrayList<Station> getAllStation() {
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