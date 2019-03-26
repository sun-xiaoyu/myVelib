package station;

import java.util.Random;

import bicycle.Bicycle;
import bicycle.EBike;
import bicycle.MBike;
import ride.Server;

public class Slot {
	private int slotId;
	private boolean occupied;
	private static int idConstructor;
	private Bicycle bicycleInThisSlot;
	
	/**
	 * vacant initialization
	 */
	public Slot() {
		super();
		this.slotId = ++idConstructor;
		/**
		 * initially no bicycle in the bicycle slot so equals to -1 in order to avoid repeat with any real bicycleId.
		 */
	}

	/**
	 * probability initialization
	 */
	public Slot(double probaBike, double probaEBike) {
		super();
		this.slotId = ++idConstructor;
		Random random = new Random();
		if (random.nextFloat() < probaBike) {
			this.occupied = true;
			if (random.nextFloat() < probaEBike) {
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
	
	public void addEleBicycle(){
		if (!this.occupied) {
			EBike eBike = new EBike();
			this.occupied = true;
			this.setBicycleInThisSlot(eBike);
		}else Server.error("Slot occupied!");
	}

	public void addMecBicycle(){
		if (!this.occupied) {
			MBike mBike = new MBike();
			this.occupied = true;
			this.setBicycleInThisSlot(mBike);
		}else Server.error("Slot occupied!");
	}
	//TODO use pattern to get add methods more flexible.

	public void removeBicycle() {
		this.bicycleInThisSlot = null;
		this.occupied = false;
	}
	
	@Override
	public String toString() {
		return "Slot [slotId=" + slotId + ", occupied=" + occupied + ", bicycleInThisSlot=" + bicycleInThisSlot + "]";
	}

	public void restore(Bicycle bike) {
		this.bicycleInThisSlot = bike;
		this.occupied = true;
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
