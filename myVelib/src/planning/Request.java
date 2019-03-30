package planning;

import java.util.Calendar;

import ride.Server;
import ride.User;
import station.GPS;
/**
 * a request sent by a user with its location, bike type preference, policy preference and send time
 * so that our system can respond to him an answer
 * @author zhihao Li
 *
 */
public class Request {
	private User user;
	private GPS startPos, endPos;
	private String bikeType;
	private String policy;
	private long sendTime;
	/**
	 * 
	 * @param user request user
	 * @param startPos user's current position
	 * @param endPos user's expected end position
	 * @param bikeType bike type preference
	 * @param policy policy type preference
	 * @throws Exception several exceptions in parameters
	 */
	public Request(User user, GPS startPos, GPS endPos, String bikeType, String policy) throws Exception{
		super();
		this.user = user;
		this.startPos = startPos;
		this.endPos = endPos;
		boolean flag = false;
		for(String string: Server.getBikeTypes()) {
			if(bikeType.equalsIgnoreCase(string)) {
				flag = true;
				break;
			}
		}
		if(flag == false) {
			throw new Exception("bikeTpye illegal");
		}
		flag = false;
		this.bikeType = bikeType;
		for(String string: Server.getPolicies()) {
			if(policy.equalsIgnoreCase(string)) {
				flag = true;
				break;
			}
		}
		if(flag == false) {// need addition of all policies
			throw new Exception("bikeTpye illegal");
		}
		this.policy = policy;
		this.sendTime = Calendar.getInstance().getTimeInMillis();
	}
	
	public String getBikeType() {
		return bikeType;
	}

	public String getPolicy() {
		return policy;
	}
	
	public void setStartPos(GPS startPos) {
		this.startPos = startPos;
	}

	public void setEndPos(GPS endPos) {
		this.endPos = endPos;
	}

	public void setBikeType(String bikeType) {
		this.bikeType = bikeType;
	}

	public void setPolicy(String policy) {
		this.policy = policy;
	}

	public User getUser() {
		return user;
	}
	public GPS getStartPos() {
		return startPos;
	}
	public GPS getEndPos() {
		return endPos;
	}
	public long getSendTime() {
		return sendTime;
	}
	/**
	 * rewrite toString method of request
	 */
	@Override
	public String toString() {
		return "Request [user=" + user.getUserId() + ", startPos=" + startPos + ", endPos=" + endPos + ", bikeType=" + bikeType
				+ ", policy=" + policy + ", sendTime=" + sendTime + "]";
	}
	
	
}
