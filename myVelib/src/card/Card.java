package card;

import ride.User;
/**
 * build Card to get different kinds of calculation for users.
 * @author Zhihao Li
 *
 */
public class Card {
	private User user;
	private int timeCredit = 0;
	/**
	 * every user has a card(or no card)
	 * @param user card holder
	 */
	public Card(User user) {
		super();
		this.user = user;
	}
	
	public User getUser() {
		return user;
	}

	public int getTimeCredit() {
		return timeCredit;
	}

	/**
	 * if returning a bike to plus station, time credit += 5
	 * @param i number of time credit to be added(generally 5)
	 */
	public void addCredit(int i) {
		this.timeCredit += i;
		
	}
	/**
	 * 
	 * @param timeCredit to offset cost of ridings
	 */
	public void setTimeCredit(int timeCredit) {
		this.timeCredit = timeCredit;
	}
	
	
	
	
}
