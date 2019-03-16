package myVelib;

import java.util.Random;

public class Slot {
	private int slotId;
	private boolean occupied;
	private static int idConstructor;
	private Bicycle bicycleInThisSlot;
	
	public Slot() {
		super();
		this.slotId = ++idConstructor;
		/**
		 * initially no bicycle in the bicycle slot so equals to -1 in order to avoid repeat with any real bicycleId.
		 */
	}

	public Slot(double probaBike, double probaEbike) {
		super();
		this.slotId = ++idConstructor;
		Random random = new Random();
		if (random.nextFloat() < probaBike) {
			this.occupied = true;
			if (random.nextFloat() < probaEbike) {
				this.bicycleInThisSlot = new EBike();
			}else this.bicycleInThisSlot = new MBike();
		}
		
	}
	

	public int getSlotId() {
		return slotId;
	}

	public void setSlotId(int slotId) {
		this.slotId = slotId;
	}

	public boolean isOccupied() {
		return occupied;
	}

	public void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}
	
	public Bicycle getBicycleInThisSlot() {
		return bicycleInThisSlot;
	}

	public void setBicycleInThisSlot(Bicycle bicycleInThisSlot) {
		this.bicycleInThisSlot = bicycleInThisSlot;
	}
	
	public void addEleBicycle() throws Exception{
		EBike eBike = new EBike();
		this.occupied = true;
		this.setBicycleInThisSlot(eBike);
	}

	public void addmecBicycle() throws Exception{
		MBike mBike = new MBike();
		this.occupied = true;
		this.setBicycleInThisSlot(mBike);
	}
	//TODO use pattern to get add methods more flexible.

	@Override
	public String toString() {
		return "Slot [slotId=" + slotId + ", occupied=" + occupied + ", bicycleInThisSlot=" + bicycleInThisSlot + "]";
	}


	/**
	 * TODO method moveBicycle to move a bicycle from a slot to another
	public void moveBicycle(Bicycle toSlot) throws Exception{
		if(toSlot == null) {
			throw new Exception("aim slot doesn't exist.");
		}
		else if(this.bicycleInThisSlot.isRidingStatus() == true) {
			throw new Exception("this bicycle is riding.");
		}
		if(this.bicycleInThisSlot)
	}
	**/
	
	
}
