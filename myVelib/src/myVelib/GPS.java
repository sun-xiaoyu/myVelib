package myVelib;

import java.util.Random;

public class GPS {
	private double x,y;

	public GPS(double x, double y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	public GPS() {
		super();
		Random random = new Random();
		this.x = random.nextDouble()*Map.getInstance().getSizeX();
		this.y = random.nextDouble()*Map.getInstance().getSizeX();
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof GPS) {
			GPS p1 = (GPS) obj;
			return ((Math.abs((p1.getX() - this.x)) < 0.1) && (Math.abs((p1.getY() - this.y)) < 0.1));
		}
		return false;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	@Override
	public String toString() {
		return "GPS [x=" + x + ", y=" + y + "]";
	}
	
	
	
}