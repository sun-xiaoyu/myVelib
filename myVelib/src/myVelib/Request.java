package myVelib;

import java.util.Calendar;

public class Request {
	private User user;
	private GPS startPos, endPos;
	private char bikeType;
	private String policy;
	private long sendTime;
	

	
	public Request(User user, GPS startPos, GPS endPos, char bikeType, String policy) throws Exception{
		super();
		this.user = user;
		this.startPos = startPos;
		this.endPos = endPos;
		if(!(bikeType == 'E') && !(bikeType == 'M')) {
			throw new Exception("bikeTpye illegal");
		}
		this.bikeType = bikeType;
		if(!(policy == "FPTD") && !(policy == "MWD")) {
			throw new Exception("bikeTpye illegal");
		}
		this.policy = policy;
		this.sendTime = Calendar.getInstance().getTimeInMillis();
	}
	
	public char getBikeType() {
		return bikeType;
	}
	public void setBikeType(char bikeType) throws Exception {
		if(!((bikeType == 'e') || (bikeType == 'm'))) {
			throw new Exception("bikeType illegal");
		}
		this.bikeType = bikeType;
	}
	public String getPolicy() {
		return policy;
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
	
}
