package ride;

import card.Card;
import station.Observer;
import station.Station;
/**
 * a user can hold a card and rent bike.
 * @author Zhihao Li
 *
 */
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
	/**
	 * a default constructor
	 */
	public User() {
		super();
		this.name = "NoName";	
		this.userId = ++idConstructor;
		
	}
	/**
	 * a user with given name
	 * @param name name of user
	 */
	public User(String name) {
		super();
		this.name = name;
		this.userId = ++idConstructor;
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
	/**
	 * override toString method of user
	 */
	@Override
	public String toString() {
		return "User [userId=" + userId + ", name=" + name + ", riding=" + riding + ", card=" + card +
				 ", totalCharge=" + totalCharge;
	}
	/**
	 * to set a card to the user
	 * @param card a card to be set
	 */
	public void addCard(Card card) {
		this.withCard = true;
		this.card = card;
	}
	/**
	 * to record ride times and duration of the user after a riding,
	 * and give 5 minutes time credit to him if bike is returned to a plus station
	 * @param record  a riding record
	 */
	public void updateStatistic(Record record) {
		this.totalRides += 1;
		this.totalRidingTimeInMIn += record.getLengthInMin();
		if (record.getEndStation().isPlus()) totalTimeCreditEarned += 5; 
	}
	/**
	 * observer pattern, user is an observer to be notified with returnability of told end station
	 */
	@Override
	public void update(Station station) {
		System.out.println(this.getName()+ ", station " + station.getStationId() + " is offline or full, you can not return bike to it.");
	}
	/**
	 * to display user's information(like toString)
	 */
	public void displayStat() {
		Server.log("User [userId=" + userId + ", name=" + name + ", totalRides=" + totalRides
				+ ", totalRidingTimeInMIn=" + totalRidingTimeInMIn + ", totalCharge=" + totalCharge
				+ ", totalTimeCreditEarned=" + totalTimeCreditEarned + "]");
	}
	/**
	 * user pay for a ride and get related information noted down
	 * @param ride a ride to be charged
	 */
	public void payFor(OngoingRide ride) {
		// TODO Auto-generated method stub
		totalCharge += ride.charge();
		card.setTimeCredit(card.getTimeCredit() - ride.getTimeCreditUsed());
		ride.setPaid();
		if (ride.getEndStation().isPlus() && this.isWithCard()) card.addCredit(5);
		
	}	
	
}
