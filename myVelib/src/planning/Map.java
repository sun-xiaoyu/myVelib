package planning;

import java.util.HashMap;
import java.util.Random;

import bicycle.EBike;
import bicycle.MBike;
import station.Slot;
import station.Station;

/**
 * A map regroups all the stations.
 * @author SUNXIAOYU
 *
 */
public class Map {
	/**
	 * size in kilometer and speed in km/min. map to hold number information. single pattern used.
	 */
	private HashMap<Integer, Station> stations;
	private String name;
	private int stationNum;
	private int totalSlotNum;
	private int totalBicycleNum;
	private int eleTotalBicycleNum;
	private int mecTotalBicycleNum;
	private static Map instance = null;
	private double sizeX,sizeY;
	
	private Map() {}

	/*
	 * first case that station number is less than general bicycle number.(i.e enough to set each station with at least one mechanic bicycle.)
	 */

	/**
	 * this function initialize the map, where stations are distributed on a square grid, the side of which is 4km.
	 * The square grid is just right to fit into the map size given by the minimum of sizeX and sizeY
	 * @param stationNum station number
	 * @param totalSlotNum slot number
	 * @throws Exception several exceptions in initialization
	 */
	public void init(int stationNum, int totalSlotNum) throws Exception{
		if((stationNum < 1) || (totalSlotNum <1)) {
			throw new Exception("input number illeagal");
		}
		if(stationNum > totalSlotNum) {//to avoid that 
			throw new Exception("station number can not be more than total slot number");
		}
		else if(stationNum > 2.05*totalSlotNum) {//to avoid that 
			throw new Exception("bicycles not enough for general initialization");
		}
		this.stations = new HashMap<Integer, Station>();
		this.stationNum = stationNum;
		this.totalSlotNum = totalSlotNum;
		this.totalBicycleNum = (int)(0.7 * this.totalSlotNum);
		this.mecTotalBicycleNum = (int) (0.7 * this.totalBicycleNum);
		this.eleTotalBicycleNum = this.totalBicycleNum - this.mecTotalBicycleNum;
		for(int i = 0; i < stationNum; i++) {//initialize numbers
			Random random = new Random();
			float plusFlag = random.nextFloat(); 
			boolean plus;
			if(plusFlag < 0.2) {//probability of 0.2 to set a station into plus
				plus = true;}
			else {
				plus = false;}
			Station station = new Station(plus, (int) (min(sizeX+1,sizeY+1)/4));
			this.stations.put(station.getStationId(),station);
		}
		for(Station station: stations.values()) {
			station.addSlot(new Slot());
		}
		Random random = new Random();
		for(int i = 0; i < totalSlotNum - stationNum; i++) {//to finish initializing slot distribution in all stations randomly
			 int index = random.nextInt(stations.size());
			 Station s = stations.get(index+1);
			 s.addSlot(new Slot());
		}
		
		int alreadySetEleNum = 0;
		while(alreadySetEleNum < eleTotalBicycleNum) {
			int index = random.nextInt(stations.size());
			 Station s = stations.get(index+1);
			 for(Slot slot: s.getSlots()) {
				 if(!slot.isOccupied()) {
					 slot.setBicycleInThisSlot(new EBike());
					 alreadySetEleNum += 1;
					 break;
				 }
			 }
		}
		
		int alreadySetMecNum = mecTotalBicycleNum;
		while(alreadySetMecNum < mecTotalBicycleNum) {
			int index = random.nextInt(stations.size());
			 Station s = stations.get(index+1);
			 for(Slot slot: s.getSlots()) {
				 if(!slot.isOccupied()) {
					 slot.setBicycleInThisSlot(new MBike());
					 alreadySetMecNum += 1;
					 break;
				 }
			 }
		}	
		instance = this; 
	}
	/**
	 * to get the smaller number in two numbers
	 * @param a the first number
	 * @param b the second number
	 * @return the smaller number
	 */
	private double min(double a, double b) {
		// TODO Auto-generated method stub
		return a<b ? a:b;
	}

	/**
	 * 	 * This correspond to the command 
	 * setup name nstations nslots sidearea nbikes: to create a myVelib network with given name and
	 * consisting of nstations stations with nslots parking slots and such that stations
	 * are arranged on a square grid whose of side sidarea and initially populated with a 
	 * nbikes bikes randomly distributed over the stations
	 * @param stationNum station total number
	 * @param totalSlotNum slot total number
	 * @param totalBicycleNum bike total number
	 * @throws Exception several exceptions in initialization
	 */
	public void init(int stationNum, int totalSlotNum, int totalBicycleNum) throws Exception{
		if((stationNum < 1) || (totalSlotNum <1) || (totalBicycleNum <1)) {
			throw new Exception("input number illeagal");
		}
		if(stationNum > totalSlotNum) {//to avoid that 
			throw new Exception("station number can not be more than total slot number");
		}
		else if(stationNum > 2.05*totalSlotNum) {//to avoid that 
			throw new Exception("bicycles not enough for general initialization");
		}
		else if(totalSlotNum > totalBicycleNum) {
			throw new Exception("bicycle number larger than slot number");
		}
		this.stations = new HashMap<Integer, Station>();
		this.stationNum = stationNum;
		this.totalSlotNum = totalSlotNum;
		this.totalBicycleNum = totalBicycleNum;
		this.mecTotalBicycleNum = (int) (0.7 * this.totalBicycleNum);
		this.eleTotalBicycleNum = this.totalBicycleNum - this.mecTotalBicycleNum;
		for(int i = 0; i < stationNum; i++) {//initialize numbers
			Random random = new Random();
			float plusFlag = random.nextFloat(); 
			boolean plus;
			if(plusFlag < 0.2) {//probability of 0.2 to set a station into plus
				plus = true;}
			else {
				plus = false;}
			Station station = new Station(plus, (int) (min(sizeX+1,sizeY+1)/4));
			this.stations.put(station.getStationId(),station);
		}
		for(Station station: stations.values()) {
			station.addSlot(new Slot());
		}
		Random random = new Random();
		for(int i = 0; i < totalSlotNum - stationNum; i++) {//to finish initializing slot distribution in all stations randomly
			 int index = random.nextInt(stations.size());
			 Station s = stations.get(index+1);
			 s.addSlot(new Slot());
		}
		
		int alreadySetEleNum = 0;
		while(alreadySetEleNum < eleTotalBicycleNum) {
			int index = random.nextInt(stations.size());
			 Station s = stations.get(index+1);
			 for(Slot slot: s.getSlots()) {
				 if(!slot.isOccupied()) {
					 slot.setBicycleInThisSlot(new EBike());
					 alreadySetEleNum += 1;
					 break;
				 }
			 }
		}
		
		int alreadySetMecNum = mecTotalBicycleNum;
		while(alreadySetMecNum < mecTotalBicycleNum) {
			int index = random.nextInt(stations.size());
			 Station s = stations.get(index+1);
			 for(Slot slot: s.getSlots()) {
				 if(!slot.isOccupied()) {
					 slot.setBicycleInThisSlot(new MBike());
					 alreadySetMecNum += 1;
					 break;
				 }
			 }
		}	
		instance = this; 
	}

	/**
	 * 
	 * This correspond to the command 
	 * setup velibnetworkName: to create a myVelib network with given name and
	 * consisting of 10 stations each of which has 10 parking slots and such that stations
	 * are arranged on a square grid whose of side 4km and initially populated with a 75%
	 * bikes randomly distributed over the 10 stations
	 */
	public void init() {
		this.sizeX = 40;
		this.sizeY = 40;
		this.stations = new HashMap<Integer, Station>();
		for (int i=0;i<10;i++) {
			Station s = new Station(10,0.7,0.3);
			this.stations.put(s.getStationId(),s);
		}
		this.stationNum = 10;
		for(Integer key : this.stations.keySet()) {
			this.totalSlotNum += this.stations.get(key).getSlotNum();
			for(Slot slot: this.stations.get(key).getSlots()) {
				if(slot.isOccupied()) {
					this.totalBicycleNum += 1;
					if(slot.getBicycleInThisSlot().getType() == 'm') {
						this.mecTotalBicycleNum += 1;
					}
					else {
						this.eleTotalBicycleNum += 1;
					}
				}
			};
		}
		instance = this;
	}
	
	public static Map getInstance() {
		if (instance == null) {
			instance = new Map();
		}
		return instance;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getSizeX() {
		return sizeX;
	}

	public double getSizeY() {
		return sizeY;
	}
	
	public void setSizeX(double sizeX) {
		this.sizeX = sizeX;
	}

	public void setSizeY(double sizeY) {
		this.sizeY = sizeY;
	}

	public int getStationNum() {
		return stationNum;
	}
	
	public int getTotalSlotNum() {
		return totalSlotNum;
	}

	public void setTotalSlotNum(int totalSlotNum) {
		this.totalSlotNum = totalSlotNum;
	}

	public HashMap<Integer, Station> getStations() {
		return stations;
	}

	public int getTotalBicycleNum() {
		return totalBicycleNum;
	}

	public int getEleTotalBicycleNum() {
		return eleTotalBicycleNum;
	}

	public int getMecTotalBicycleNum() {
		return mecTotalBicycleNum;
	}

	public void addStation(Station s) {
		this.stations.put(s.getStationId(),s);
		this.stationNum += 1;
	}
	
	/*
	public void delStation(Station s) throws Exception{
		if(this.stationList.contains(s)) {
			throw new Exception("this station is not in the station list.");
		}
		ArrayList<Slot> slotsToDelete = s.getSlots();
		for(Slot slot: slotsToDelete) {
			slot.getBicycleInThisSlot().setRidingStatus(true);
			slot = null;
		}
		this.stationList.remove(s);
		this.stationNum -= 1;
		if(this.stationList.isEmpty()) {
			throw new Exception("no station any more");
		}
	}
	*/
	/**
	 * override map toString method
	 */
	@Override
	public String toString() {
		String stationListstr = "";
		for (Station s: stations.values()) {
			stationListstr += s.toString()+"\n\n";
		}
		return "Map [stationList=\n" + stationListstr + ", stationNum=" + stationNum + ", totalSlotNum=" + totalSlotNum
				+ ", totalBicycleNum=" + totalBicycleNum + ", eleTotalBicycleNum=" + eleTotalBicycleNum
				+ ", mecTotalBicycleNum=" + mecTotalBicycleNum + "]";
	}
	/**
	 * to initialize from a file
	 * @param filepath file path to find
	 */
	public void initFromFile(String filepath) {
		// TODO
	}
	/**
	 * to display all stationIDs in this map
	 */
	public void display() {
		System.out.print("Stations ID = [");
		for (Station station : stations.values()) {
			System.out.print(station.getStationId()+" ");
		}
		System.out.println("]");
		// TODO Auto-generated method stub
		
	}
	
//	map.serize;
//	map.deserialize;
//	map.show/display;
	


}
