package myVelib;

import java.util.ArrayList;
import java.util.Random;

public class Station {
	private int stationId;
	private boolean offline;
	private GPS pos;
	private boolean plus;
	private ArrayList<Slot> slots;
	private int spareSlotNum;
	private int slotNum;
	private static int idConstructor;
	private boolean full;
	
	public Station(boolean plus) {//general initialization
		super();
		this.plus = plus;
		this.stationId = ++idConstructor;
		this.slots = new ArrayList<Slot>();
		Random random = new Random();
		this.pos = new GPS(random.nextInt(11)*4,random.nextInt(11)*4);
		this.full = this.judgeFull();}
	
	public Station(int slotnum) {//0.7,0.3 initialization
		super();
		Random random = new Random();
		this.pos = new GPS(random.nextInt(11)*4,random.nextInt(11)*4);
		this.plus = (boolean)(random.nextFloat()>0.6);	
		this.stationId = ++idConstructor;
		this.slotNum = slotnum;
		this.slots = new ArrayList<Slot>();
		for (int i =0;i<slotnum;i++) {
			this.slots.add(new Slot(0.7, 0.3));
		}
		for (int i =0;i<slotnum;i++) {
			if (this.slots.get(i).getBicycleInThisSlot() == null) this.spareSlotNum++;
		}
		this.full = this.judgeFull();
	}

	public Station() {
		super();
		Random random = new Random();
		this.pos = new GPS(random.nextInt(11)*4,random.nextInt(11)*4);
		this.plus = (boolean)(random.nextFloat()>0.6);	
		this.stationId = ++idConstructor;
		this.slots = new ArrayList<Slot>();
		this.full = this.judgeFull();
	}
	
	int getStationId() {
		return stationId;
	}

	public boolean isOffline() {
		return offline;
	}

	public void setOffline(boolean offline) {
		this.offline = offline;
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
		return spareSlotNum;
	}

	public boolean isFull() {
		return full;
	}

	public void setFull(boolean full) {
		this.full = full;
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

	public void addSlot(Slot slot) {
		this.slots.add(slot);
		this.slotNum += 1;
		this.spareSlotNum += 1;
	}
	
	public void delSlot(Slot slot) throws Exception,IllegalArgumentException{
		if(this.slots.contains(slot)) {
			if(slot.isOccupied() == false) {
				this.spareSlotNum -= 1;
				if(this.spareSlotNum < 0) {
					throw new Exception("spareSlotNum < 0");
				}
			}
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
					slot.setOccupied(false);
					this.spareSlotNum += 1;
					if(this.spareSlotNum > slotNum) {
						throw new Exception("spareSlotNum > slotNum");
					}
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
		s.setOccupied(true);
		b.setRidingStatus(false);
		this.spareSlotNum -= 1;
		if(spareSlotNum < 0) {
			throw new Exception("spareSlotNum < 0");
		}
	}

	@Override
	public String toString() {
		return "Station [stationId=" + stationId + ", offline=" + offline + ", pos=" + pos + ", plus=" + plus
				+ ", slots=" + slots + ", spareSlotNum=" + spareSlotNum + ", slotNum=" + slotNum + ", full=" + full
				+ "]";
	}


	
	
	
	


}