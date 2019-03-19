package myVelib;

public class User implements Observer{
	private int userId;
	private String name;
	private boolean riding;
	private boolean withCard;
	private Card card;
	private int totalRides;
	private double totalTime;
	private double totalCharge;
	private static int idConstructor;
	private String policy;
	
	public User() {
		super();
		this.name = "NoName";
		String strId = String.format("%5d", idConstructor).replace(" ", "0"); 
		int intId = Integer.parseInt(strId);		
		this.userId = intId;
		idConstructor += 1;
		this.withCard = false;
		this.riding = false;
		this.totalRides = 0;
		this.totalTime = 0;
		this.totalCharge = 0;
	}
	
	public User(String name) {
		super();
		this.name = name;
		String strId = String.format("%5d", idConstructor).replace(" ", "0"); 
		int intId = Integer.parseInt(strId);		
		this.userId = intId;
		idConstructor += 1;
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
	public double getTotalTime() {
		return totalTime;
	}
	public void setTotalTime(float totalTime) {
		this.totalTime = totalTime;
	}
	public double getTotalCharge() {
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
	
	/**
	 * 
	 */
	
	@Override
	public void update(Station station) {
		System.out.println(this.getName()+ ", station" + station.getStationId() + "is full,you can not return bike to it.");
	}

	public void payFor(OngoingRide ride) {
		// TODO Auto-generated method stub
		this.totalCharge += ride.charge();
		this.card.setTimeCredit(this.card.getTimeCredit() - ride.timeCreditUsed);
		ride.setPaid();
		
	}
	
	
}
