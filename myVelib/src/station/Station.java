package station;

import java.util.ArrayList;
import java.util.Random;

import bicycle.Bicycle;
import ride.Server;
/**
 * Station holds several slots where users can rend bikes. 
 * @author Zhihao Li
 */
public class Station {
	private int stationId;
	private boolean offline;
	private GPS pos;
	private boolean plus;
	private ArrayList<Slot> slots;
	private int totalRent,totalReturn;
	private int slotNum;
	private static int idConstructor;
	private boolean full;
	private static ArrayList<Station> existedStations = new ArrayList<Station>();
	private ObservableStation returnObservableStation = new ObservableStation(this);
	
	/**
	 * general initialization
	 * @param plus if it is a plus station
	 * @param gridSize edge length of the grid
	 * @throws Exception several exceptions to be solved
	 */
	public Station(boolean plus, int gridSize) throws Exception {
		super();
		if(gridSize <= 1) {
			
			throw new Exception("gridSize should be bigger than 1");
		}
		Random random = new Random();
		this.plus = plus;
		this.stationId = ++idConstructor;
		this.slots = new ArrayList<Slot>();
		this.full = this.isFull();
		GPS gps;
		boolean newGPSFlag = false;
		do {
			gps = new GPS(random.nextInt(gridSize+1)*4,random.nextInt(gridSize+1)*4);
			for(Station s: existedStations) {
				if(s.getPos().equals(gps)) {
					newGPSFlag = true;
				}
			}
		}while(newGPSFlag);
		this.pos = gps;
		existedStations.add(this);
	}
	
	/**
	 * probability constructor
	 * @param slotnum total slot number
	 * @param probaBike bike generating probability
	 * @param probaEBike electric bike generating probability
	 */
	public Station(int slotnum, double probaBike, double probaEBike) {//0.7,0.3 initialization
		super();
		Random random = new Random();
		this.plus = (boolean)(random.nextFloat()>0.6);	
		this.stationId = ++idConstructor;
		this.slotNum = slotnum;
		this.slots = new ArrayList<Slot>();
		for (int i =0;i<slotnum;i++) {
			this.slots.add(new Slot(probaBike, probaEBike));
		}
		this.full = this.isFull();
		GPS gps;
		boolean newGPSFlag = false;
		do {
			gps = new GPS(random.nextInt(11)*4,random.nextInt(11)*4);
			for(Station s: existedStations) {
				if(s.getPos().equals(gps)) {
					newGPSFlag = true;
					break;
				}
			}
		}while(newGPSFlag);
		this.pos = gps;
		existedStations.add(this);
	}
	/**
	 * special initialization
	 * grid size 4km * 4km , 10 *10 grid
	 * 0.4 probability of plus(chosen)
	 */
	public Station() {
		super();
		Random random = new Random();
		this.plus = (boolean)(random.nextFloat()>0.6);	
		this.stationId = ++idConstructor;
		this.slots = new ArrayList<Slot>();
		this.full = this.isFull();
		GPS gps;
		boolean newGPSFlag = false;
		do {
			gps = new GPS(random.nextInt(11)*4,random.nextInt(11)*4);
			for(Station s: existedStations) {
				if(s.getPos().equals(gps)) {
					newGPSFlag = true;
				}
			}
		}while(newGPSFlag);
		this.pos = gps;
		existedStations.add(this);
	}
	
	public int getStationId() {
		return stationId;
	}

	public boolean isOffline() {
		return offline;
	}
	/**
	 * set offline condition of a station
	 * if to shut down, delete all observer of this station
	 * @param offline set offline or online
	 */
	public void setOffline(boolean offline) {
		if(offline == true && this.offline == false) {
			this.returnObservableStation.setAvailability();
			this.returnObservableStation.deletAllObservers();
		}
		this.offline = offline;
	}

	public GPS getPos() {
		return pos;
	}

	public void setStationId(int stationId) {
		this.stationId = stationId;
	}

	public boolean isPlus() {
		return plus;
	}

	public void setPlus(boolean plus) {
		this.plus = plus;
	}

	public ArrayList<Slot> getSlots() {
		return slots;
	}

	public int getSlotNum() {
		return slotNum;
	}
	
	/**
	 * to calculate spare slot number in this station
	 * @return spare slot number in this station
	 */
	public int getSpareSlotNum() {
		int spareSlotNum = 0;
		for(Slot slot: this.slots) {
			if(!slot.isOccupied()) {
				spareSlotNum +=1;
			}
		}		
		return spareSlotNum;
	}
	/**
	 * to observe if this station is full
	 * @return full or not
	 */
	public boolean isFull() {
		this.full = true;
		for(Slot slot: this.slots) {
			if(slot.isOccupied() == false) {
				this.full = false;
				break;
			}
		}
		return this.full;
	}
	/**
	 * to calculate available electric number in this station
	 * @return available electric number in this station
	 */
	public int getEBicycleNumber() {
		int eBicycleNumber = 0;
		for(Slot slot: this.slots) {
			if(slot.isOccupied() && (slot.getBicycleInThisSlot().getType() =='E')) {
				eBicycleNumber +=1;
			}
		}
		return eBicycleNumber;
	}

	/**
	 * to calculate available mechanic number in this station
	 * @return available mechanic number in this station
	 */
	public int getMBicycleNumber() {
		int mBicycleNumber = 0;
		for(Slot slot: this.slots) {
			if(slot.isOccupied() && (slot.getBicycleInThisSlot().getType() =='M')) {
				mBicycleNumber +=1;
			}
		}
		return mBicycleNumber;
	}
	/**
	 * to calculate available number in this station
	 * @return available number in this station
	 */
	public int getBicycleNumber() {
		int bicycleNumber = 0;
		for(Slot slot: this.slots) {
			if(slot.isOccupied()) {
				bicycleNumber +=1;
			}
		}
		return bicycleNumber;
	}
	/**
	 * observer pattern, to get this concrete station observable
	 * @return return the concrete observable
	 */
	public ObservableStation getReturnObservableStation() {
		return returnObservableStation;
	}
	/**
	 * add a new slot to this station
	 * @param slot the new slot
	 */
	public void addSlot(Slot slot) {
		this.slots.add(slot);
		this.slotNum += 1;
	}
	/**
	 * 
	 * @param slot the slot to be deleted
	 * @throws IllegalArgumentException may given illegal argument that slot number less than 0 or wrong station
	 */
	public void delSlot(Slot slot) throws IllegalArgumentException{
		if(this.slots.contains(slot)) {
			this.slots.remove((Object)slot);
			if(this.slotNum < 1) {
				throw new IllegalArgumentException("slotNum < 0");
			}
			this.slotNum -= 1;
			
		}
		else {
			throw new IllegalArgumentException("this slot is not in the given station.");
		}
	}

	
	public void rentBicycle(Bicycle b) throws Exception {
		try {
			for(Slot slot: this.slots) {
				if(slot.getBicycleInThisSlot() == b) {
					b.setRidingStatus(true);
					slot.removeBicycle();
					break;
				}
			}
		}
		catch(IllegalArgumentException e) {
			System.out.println("exception:" + e + ", given bicycle doesn't exist in any slot of this station.");
		}
	}
	
	public void returnBicycle(Bicycle b, Slot s) throws Exception,IllegalArgumentException{
		if(s.isOccupied() ==true) {
			throw new IllegalArgumentException("this slot is already occupied, can't return bicycle to it");
		}
		else if(b.isRidingStatus() == false) {
			throw new IllegalArgumentException("this bike is not riding, can't be returned back");
		}
		else if(this.getSpareSlotNum() == 0) {
			throw new IllegalArgumentException("this station has no spare slot");
		}
		s.restore(b);
		b.setRidingStatus(false);
	}
	
	public int getTotalRent() {
		return totalRent;
	}

	public int getTotalReturn() {
		return totalReturn;
	}
	
	/**
	 * add add rent number of this station
	 * @param number rent number to be added
	 */	
	public void addTotalRent(int number) {
		this.totalRent += number;
	}
	/**
	 * add add return number of this station
	 * @param number return number to be added
	 */
	public void addTotalReturn(int number) {
		this.totalReturn += number;
	}
	/**
	 * for screen display
	 */
	public void displayStat() {
		Server.log("Station [stationId=" + stationId + ", offline=" + offline + ", pos=" + pos + ", plus=" + plus
				+ ", totalRent=" + totalRent + ", totalReturn=" + totalReturn + ", slotNum=" + slotNum + "]");
	}

	@Override
	public String toString() {
		String slotsstr = "";
		for (Slot s: slots) {
			slotsstr += '\t'+ s.toString()+"\n";
		}
		return "Station [stationId=" + stationId + ", offline=" + offline + ", pos=" + pos + ", plus=" + plus
				+ ", slots=\n" + slotsstr + ", spareSlotNum=" + this.getSpareSlotNum() + ", slotNum=" + this.getSlotNum() + ", full=" + full
				+ ", eBicycleNumber=" + this.getEBicycleNumber() + ", mBicycleNumber=" + this.getMBicycleNumber() + "]";
	}


}