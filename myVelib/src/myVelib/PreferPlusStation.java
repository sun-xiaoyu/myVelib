package myVelib;

import java.util.ArrayList;

public class PreferPlusStation implements PlanningAlgo{
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
			throw new Exception("no available station containing given type of bicycle");
		}
		
		Station minStartStation = givenTypeAvaStations.get(0);
		double minStartDis = Math.sqrt((minStartStation.getPos().getX() - startPoint.getX()) * (minStartStation.getPos().getX() - startPoint.getX()) + 
				(minStartStation.getPos().getY() - startPoint.getY())*(minStartStation.getPos().getY() - startPoint.getY()));
		
		Station minEndStation = curDis.getRetunableStationList().get(0);
		double minEndDis = Math.sqrt((minEndStation.getPos().getX() - endPoint.getX()) * (minEndStation.getPos().getX() - endPoint.getX()) + 
				(minEndStation.getPos().getY() - endPoint.getY())*(minEndStation.getPos().getY() - endPoint.getY()));
		
		for(Station s: givenTypeAvaStations) {
			double startDis = Math.sqrt((s.getPos().getX() - startPoint.getX())*(s.getPos().getX() - startPoint.getX()) + 
				(s.getPos().getY() - startPoint.getY()) * (s.getPos().getY() - startPoint.getY()));
			if( startDis < minStartDis) {
				minStartStation = s;
				minStartDis = startDis; 
			}
		}
		
		for(Station s: curDis.getRetunableStationList()) {
			double endDis = Math.sqrt((s.getPos().getX() - endPoint.getX())*(s.getPos().getX() - endPoint.getX()) + 
					(s.getPos().getY() - endPoint.getY()) * (s.getPos().getY() - endPoint.getY()));
			if( endDis < minEndDis) {
				minEndStation = s;
				minEndDis = endDis; 
			}
		}
		/**
		 * to get plus returnable station of minimal distance to destination.
		 */
		Station minDisPlusReturnableStation = null;
		double plusMinEndDis = 9999999;
		for(Station s: curDis.getRetunableStationList()) {
			double plusEndDis = Math.sqrt((s.getPos().getX() - endPoint.getX()) * (s.getPos().getX() - endPoint.getX()) + 
					(s.getPos().getY() - endPoint.getY())*(s.getPos().getY() - endPoint.getY()));
			if(s.isPlus() && (plusEndDis < 1.1*minEndDis) && (plusEndDis < plusMinEndDis)) {
				plusMinEndDis = plusEndDis;
				minDisPlusReturnableStation = s;
			}
		}
		if(minDisPlusReturnableStation != null) {
			minEndDis = plusMinEndDis;
			minEndStation = minDisPlusReturnableStation;
		}

		double minTime = (minStartDis + minEndDis) / Server.walkingSpeed + 
				(Math.abs(minStartStation.getPos().getX() - minEndStation.getPos().getX()) +
				Math.abs(minStartStation.getPos().getY() - minEndStation.getPos().getY()))/ridingSpeed;
		double minDis = minStartDis + minEndDis + 
				(Math.abs(minStartStation.getPos().getX() - minEndStation.getPos().getX()) +
				Math.abs(minStartStation.getPos().getY() - minEndStation.getPos().getY()));
		if(minTime > 500) {
			throw new Exception("BikeType input illegal.");
		}
		
		
		Answer ans = new Answer(minStartStation, minEndStation, minTime, minDis);
		return ans;
		
	}
	
}

