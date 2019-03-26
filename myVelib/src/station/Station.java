package station;

import java.util.ArrayList;
import java.util.Random;

import bicycle.Bicycle;
import ride.Server;

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
	 * @throws Exception
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
		this.full = this.judgeFull();
		GPS gps;
		boolean newGPSFlag = false;
		do {
			gps = new GPS(random.nextInt(gridSize)*4,random.nextInt(gridSize)*4);
			for(Station s: existedStations) {
				if(s.getPos().equals(gps)) {
					newGPSFlag = true;
				}
			}
		}while(newGPSFlag);
		this.pos = gps;
		existedStations.add(this);
	}
	
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
		this.full = this.judgeFull();
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

	/**
	 * grid size 4km * 4km , 10 *10 grid
	 * 0.4 probability of plus(chosen)
	 */
	public Station() {
		super();
		Random random = new Random();
		this.plus = (boolean)(random.nextFloat()>0.6);	
		this.stationId = ++idConstructor;
		this.slots = new ArrayList<Slot>();
		this.full = this.judgeFull();
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

	public void setOffline(boolean offline) {
		this.offline = offline;
		this.returnObservableStation.setAvailability();;
	}

	public GPS getPos() {
		return pos;
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
	
	public int getSpareSlotNum() {
		int spareSlotNum = 0;
		for(Slot slot: this.slots) {
			if(!slot.isOccupied()) {
				spareSlotNum +=1;
			}
		}		
		return spareSlotNum;
	}

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

	public int getEBicycleNumber() {
		int eBicycleNumber = 0;
		for(Slot slot: this.slots) {
			if(slot.isOccupied() && (slot.getBicycleInThisSlot().getType() =='E')) {
				eBicycleNumber +=1;
			}
		}
		return eBicycleNumber;
	}


	public int getMBicycleNumber() {
		int mBicycleNumber = 0;
		for(Slot slot: this.slots) {
			if(slot.isOccupied() && (slot.getBicycleInThisSlot().getType() =='M')) {
				mBicycleNumber +=1;
			}
		}
		return mBicycleNumber;
	}
	public int getBicycleNumber() {
		int bicycleNumber = 0;
		for(Slot slot: this.slots) {
			if(slot.isOccupied()) {
				bicycleNumber +=1;
			}
		}
		return bicycleNumber;
	}

	public ObservableStation getReturnObservableStation() {
		return returnObservableStation;
	}

	public void addSlot(Slot slot) {
		this.slots.add(slot);
		this.slotNum += 1;
	}
	
	public void delSlot(Slot slot) throws Exception,IllegalArgumentException{
		if(this.slots.contains(slot)) {
			this.slots.remove((Object)slot);
			this.slotNum -= 1;
			if(this.slotNum < 0) {
				throw new Exception("slotNum < 0");
			}
			
		}
		else {
			throw new IllegalArgumentException("this slot is not in the given station.");
		}
	}
	
	public boolean judgeFull() {
		boolean flagFull = true;
		for(int i = 0; i < this.slots.size(); i++){
			if(this.slots.get(i).isOccupied() == false) {
				flagFull = false;
			}
		}
		this.full = flagFull;
		return this.full;
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