package myVelib;

public class Card {
	private User user;
	private int timeCredit = 0;
	
	public Card(User user, int timeCredit) {
		super();
		this.user = user;
		this.timeCredit = timeCredit;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getTimeCredit() {
		return timeCredit;
	}

	public void setTimeCredit(int timeCredit) {
		this.timeCredit = timeCredit;
	}
	
	
	
}
