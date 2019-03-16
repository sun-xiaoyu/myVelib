package myVelib;

public class Card {
	private User user;
	private int timeCredit = 0;
	
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

	public void addCredit(int i) {
		this.timeCredit += i;
		
	}

	public void setTimeCredit(int timeCredit) {
		this.timeCredit = timeCredit;
	}
	
	
	
	
}
