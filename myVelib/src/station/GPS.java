package station;

import java.util.Random;

import planning.Map;
/**
 * location for users and stations
 * @author Zhihao Li
 *
 */
public class GPS {
	private double x,y;
	/**
	 * generator for given position
	 * @param x x
	 * @param y y
	 */
	public GPS(double x, double y) {
		super();
		this.x = x;
		this.y = y;
	}
	/**
	 * generator without given position so randomly 
	 */
	public GPS() {
		super();
		Random random = new Random();
		this.x = random.nextDouble()*Map.getInstance().getSizeX();
		this.y = random.nextDouble()*Map.getInstance().getSizeX();
	}
	/**
	 * override position equal method so that less than 0.1 at each abscissa
	 * can be regarded as the same position
	 */
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
	/**
	 * override GPS print method
	 */
	@Override
	public String toString() {
		return "GPS [x=" + x + ", y=" + y + "]";
	}
	
	
	
}