package card;

import ride.User;
/**
 * Vmax Card, a kind of card
 * @author Zhihao Li
 *
 */
public class VmaxCard extends Card {

	/**
	 * set a vmax card to distinguish from other card types
	 * @param user Vmax card holder
	 */
	public VmaxCard(User user) {
		super(user);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return "VmaxCard";
	}
	

}
