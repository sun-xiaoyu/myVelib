package planning;

import java.util.ArrayList;
import java.util.Random;

import bicycle.EBike;
import bicycle.MBike;
import station.Slot;
import station.Station;

/**
 * A map which regroups all the stations.
 * @author SUNXIAOYU
 *
 */
public class Map {
	/**
	 * size in kilometer and speed in km/min. map to hold number information. single pattern used.
	 */
	private ArrayList <Station> stationList; 
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
	 * @param stationNum
	 * @param totalSlotNum
	 * @throws Exception
	 */
	public void init(int stationNum, int totalSlotNum) throws Exception{
		if(stationNum > totalSlotNum) {//to avoid that 
			throw new Exception("station number can not be more than total slot number");
		}
		else if(stationNum < 2.05*totalSlotNum) {//to avoid that 
			throw new Exception("bicycles not enough for general initialization");
		}
		this.stationList = new ArrayList<Station>();
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
			Station station = new Station(plus, (int) (min(sizeX,sizeY)/4));
			this.stationList.add(station);
		}
		for(Station station: stationList) {
			station.addSlot(new Slot(1,0));
		}
		
		for(int i = 0; i < totalSlotNum - stationNum; i++) {//to initialize slot distribution in all stations randomly
			 int index = (int) (Math.random()* this.stationList.size()); 
			 Station s = stationList.get(index);
			 s.addSlot(new Slot());
		}
		
		int alreadySetEleNum = 0;
		while(alreadySetEleNum < eleTotalBicycleNum) {
			 int index = (int) (Math.random()* this.stationList.size()); 
			 Station s = stationList.get(index);
			 for(Slot slot: s.getSlots()) {
				 if(!slot.isOccupied()) {
					 slot.setBicycleInThisSlot(new EBike());
					 alreadySetEleNum += 1;
					 break;
				 }
			 }
		}
		
		int alreadySetMecNum = mecTotalBicycleNum - stationNum;
		while(alreadySetMecNum < mecTotalBicycleNum) {
			 int index = (int) (Math.random()* this.stationList.size()); 
			 Station s = stationList.get(index);
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
	
	private double min(double a, double b) {
		// TODO Auto-generated method stub
		return a<b ? a:b;
	}

	/**
	 * This correspond to the command 
	 * setup <velibnetworkName>: to create a myVelib network with given name and
	 * consisting of 10 stations each of which has 10 parking slots and such that stations
	 * are arranged on a square grid whose of side 4km and initially populated with a 75%
	 * bikes randomly distributed over the 10 stations
	 * @param s length of side in km
	 * @throws Exception
	 */
	public void init() {
		this.sizeX = 40;
		this.sizeY = 40;
		this.stationList = new ArrayList <Station>();
		for (int i=0;i<10;i++) {
			Station s = new Station(10,0.7,0.3);
			this.stationList.add(s);
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

	public ArrayList<Station> getStationList() {
		return stationList;
	}
	public int getStationNum() {
		return stationNum;
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
		this.stationList.add(s);
		this.stationNum += 1;
	}
	
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

	@Override
	public String toString() {
		String stationListstr = "";
		for (Station s: stationList) {
			stationListstr += s.toString()+"\n\n";
		}
		return "Map [stationList=\n" + stationListstr + ", stationNum=" + stationNum + ", totalSlotNum=" + totalSlotNum
				+ ", totalBicycleNum=" + totalBicycleNum + ", eleTotalBicycleNum=" + eleTotalBicycleNum
				+ ", mecTotalBicycleNum=" + mecTotalBicycleNum + "]";
	}

	public void initFromFile(String filepath) {
		// TODO
	}

	public void display() {
		System.out.print("Stations ID = [");
		for (Station station : stationList) {
			System.out.print(station.getStationId()+" ");
		}
		System.out.println("]");
		// TODO Auto-generated method stub
		
	}
	
//	map.serize;
//	map.deserialize;
//	map.show/display;
	


}
