package myVelib;

import java.util.ArrayList;

public class FastPathToDestination implements PlanningAlgo{
	/*
	 * fast path to destination
	 * @see myVelib.PlanningAlgo#handle(myVelib.Request)
	 */
	@Override
	public Answer handle(Request request) throws Exception {
		GPS startPoint = request.getStartPos();
		GPS endPoint = request.getEndPos();
		CurrentDistribution curDis = CurrentDistribution.getInstance();
		ArrayList<Station> givenTypeAvaStations = null;// whether this init is a correct choice in case that ArrayList can not be covered directly 
		double ridingSpeed = 0.00001;//if not given proper bicycle type, time cost would be extremely large
		if(request.getBikeType() == 'E') {
			ridingSpeed = Server.eleRidingSpeed;
			givenTypeAvaStations = curDis.geteAvaStationList();
		}
		
		if(request.getBikeType() == 'M') {
			ridingSpeed = Server.mecRidingSpeed;
			givenTypeAvaStations = curDis.getmAvaStationList();
		}

		if(givenTypeAvaStations.size() == 0) {
			Server.error("no available station containing given type of bicycle");
		}

		Station minStartStation = givenTypeAvaStations.get(0);
		Station minEndStation = curDis.getReturnableStationList().get(0);
		
		double minEndDis = Math.sqrt((minEndStation.getPos().getX() - endPoint.getX()) * (minEndStation.getPos().getX() - endPoint.getX()) + 
				(minEndStation.getPos().getY() - endPoint.getY())*(minEndStation.getPos().getY() - endPoint.getY()));
		
		for(Station s: curDis.getReturnableStationList()) {
			double endDis = Math.sqrt((s.getPos().getX() - endPoint.getX())*(s.getPos().getX() - endPoint.getX()) + 
					(s.getPos().getY() - endPoint.getY()) * (s.getPos().getY() - endPoint.getY()));
			if( endDis < minEndDis) {
				minEndStation = s;
				minEndDis = endDis; 
			}
		}
		
		double minWalkRideTime = Math.sqrt((minStartStation.getPos().getX() - startPoint.getX()) * (minStartStation.getPos().getX() - startPoint.getX())+
				(minStartStation.getPos().getX() - startPoint.getX()) * (minStartStation.getPos().getX() - startPoint.getX()))/
				Server.walkingSpeed +
				Math.abs((minStartStation.getPos().getX() - endPoint.getX())+ 
						(minStartStation.getPos().getY() - endPoint.getY()))/
				ridingSpeed;
		
		for(Station s: givenTypeAvaStations) {
			double walkRideTime = Math.sqrt((s.getPos().getX() - startPoint.getX()) * (s.getPos().getX() - startPoint.getX())+
					(s.getPos().getX() - startPoint.getX()) * (s.getPos().getX() - startPoint.getX()))/
					Server.walkingSpeed +
					Math.abs((s.getPos().getX() - endPoint.getX())+ 
							(s.getPos().getY() - endPoint.getY()))/
					ridingSpeed;
			if( walkRideTime < minWalkRideTime) {
				minStartStation = s;
				minWalkRideTime = walkRideTime; 
			}
		}			

		double minTime = minEndDis/ Server.walkingSpeed + minWalkRideTime;
		double minDis = minEndDis + 
				Math.sqrt((minStartStation.getPos().getX() - endPoint.getX())*(minStartStation.getPos().getX() - endPoint.getX()) + 
				(minStartStation.getPos().getY() - endPoint.getY()) * (minStartStation.getPos().getY() - endPoint.getY())) +
				(Math.abs(minStartStation.getPos().getX() - minEndStation.getPos().getX()) +
				Math.abs(minStartStation.getPos().getY() - minEndStation.getPos().getY()));
		if(minTime > 500) {
			throw new Exception("BikeType input illegal.");
		}
		
		Answer ans = new Answer(minStartStation, minEndStation, minTime, minDis);
		return ans;
	}
}
