package planning;

import java.util.Calendar;

import ride.Server;
import station.GPS;
import testSenario.User;

public class Request {
	private User user;
	private GPS startPos, endPos;
	private String bikeType;
	private String policy;
	private long sendTime;

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

	@Override
	public String toString() {
		return "Request [user=" + user + ", startPos=" + startPos + ", endPos=" + endPos + ", bikeType=" + bikeType
				+ ", policy=" + policy + ", sendTime=" + sendTime + "]";
	}
	
	
}
