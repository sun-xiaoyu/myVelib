package myVelib;

public class User{
	private int userId;
	private String name;
	private boolean riding;
	private boolean withCard;
	private Card card;
	private int totalRides;
	private float totalTime;
	private float totalCharge;
	private static int idConstructor;
	private String policy;
	public User(String name) {
		super();
		this.name = name;
		this.userId = ++idConstructor;
		this.withCard = false;
		this.riding = false;
		this.totalRides = 0;
		this.totalTime = 0;
		this.totalCharge = 0;
	}
	
	public int getUserId() {
		return userId;
	}
	
	public String getName() {
		return name;
	}
	
	public boolean isRiding() {
		return riding;
	}
	public void setRiding(boolean riding) {
		this.riding = riding;
	}
	public boolean isWithCard() {
		return withCard;
	}
	
	public String getPolicy() {
		return policy;
	}

	public void setPolicy(String policy) {
		this.policy = policy;
	}

	public Card getCard() {
		return card;
	}

	public int getTotalRides() {
		return totalRides;
	}
	public void setTotalRides(int totalRides) {
		this.totalRides = totalRides;
	}
	public float getTotalTime() {
		return totalTime;
	}
	public void setTotalTime(float totalTime) {
		this.totalTime = totalTime;
	}
	public float getTotalCharge() {
		return totalCharge;
	}
	public void setTotalCharge(float totalCharge) {
		this.totalCharge = totalCharge;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", name=" + name + ", riding=" + riding + ", withCard=" + withCard
				+ ", totalRides=" + totalRides + ", totalTime=" + totalTime + ", totalCharge=" + totalCharge + "]";
	}
	
	public void addCard(Card card) {
		this.withCard = true;
		this.card = card;
	}
	
	public void settlement(float totalRides, float totalTime, float totalCharge, float rideAdd, float timeAdd, float chargeAdd) {
		this.totalRides += rideAdd;
		this.totalTime += timeAdd;
		this.totalCharge += chargeAdd;
		
	}
	
	
}