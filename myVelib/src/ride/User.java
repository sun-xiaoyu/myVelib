package ride;

import card.Card;
import card.Observer;
import station.Station;

public class User implements Observer{
	private int userId;
	private String name;
	private boolean riding;
	private boolean withCard;
	private Card card;
	private int totalRides;
	private double totalRidingTimeInMIn;
	private double totalCharge;
	private int totalTimeCreditEarned;
	private static int idConstructor;
	private String policy;
	
	public User() {
		super();
		this.name = "NoName";	
		this.userId = ++idConstructor;
	}
	
	public User(String name) {
		super();
		this.name = name;
		this.userId = ++idConstructor;
		idConstructor += 1;
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
	public double getTotalRidingTimeInMIn() {
		return totalRidingTimeInMIn;
	}
	public double getTotalCharge() {
		return totalCharge;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", name=" + name + ", riding=" + riding + ", card=" + card +
				 ", totalCharge=" + totalCharge + ", policy=" + policy + "]";
	}
	
	public void addCard(Card card) {
		this.withCard = true;
		this.card = card;
	}
	
	public void updateStatistic(Record record) {
		totalRides += 1;
		totalRidingTimeInMIn += record.getLengthInMin();
		if (record.getEndStation().isPlus()) totalTimeCreditEarned += 5; 
	}
	
	@Override
	public void update(Station station) {
		System.out.println(this.getName()+ ", station " + station.getStationId() + "is offline or full, you can not return bike to it.");
	}

	public String showStatistics() {
		return "User [userId=" + userId + ", name=" + name + ", totalRides=" + totalRides
				+ ", totalRidingTimeInMIn=" + totalRidingTimeInMIn + ", totalCharge=" + totalCharge
				+ ", totalTimeCreditEarned=" + totalTimeCreditEarned + "]";
	}

	public void payFor(OngoingRide ride) {
		// TODO Auto-generated method stub
		totalCharge += ride.charge();
		card.setTimeCredit(card.getTimeCredit() - ride.getTimeCreditUsed());
		ride.setPaid();
		if (ride.getEndStation().isPlus() && this.isWithCard()) card.addCredit(5);
		
	}
	
	
	
}
