package myVelib;

import java.util.ArrayList;

public class AvoidPlusStation implements PlanningAlgo{
	@Override
	public Answer handle(Request request, CurrentDistribution curDis) throws Exception {
		GPS startPoint = request.getStartPos();
		GPS endPoint = request.getEndPos();
		ArrayList<Station> givenTypeAvaNotPlusStations = null;// whether this init is a correct choice in case that ArrayList can not be covered directly 
		double ridingSpeed = 0.00001;//if not given proper bicycle type, time cost would be extremely large
		if(request.getBikeType() == 'E') {
			ridingSpeed = Server.eleRidingSpeed;
			givenTypeAvaNotPlusStations = curDis.geteAvaStationList();
		}
		
		if(request.getBikeType() == 'M') {
			ridingSpeed = Server.mecRidingSpeed;
			givenTypeAvaNotPlusStations = curDis.getmAvaStationList();
		}

		for(Station station: givenTypeAvaNotPlusStations) {
			if(station.isPlus()) {
				givenTypeAvaNotPlusStations.remove(station);
			}
		}
		
		if(givenTypeAvaNotPlusStations.size() == 0) {
			throw new Exception("no available station containing given type of bicycle");
		}
		
		Station minStartStation = givenTypeAvaNotPlusStations.get(0);
		double minStartDis = Math.sqrt((minStartStation.getPos().getX() - startPoint.getX()) * (minStartStation.getPos().getX() - startPoint.getX()) + 
				(minStartStation.getPos().getY() - startPoint.getY())*(minStartStation.getPos().getY() - startPoint.getY()));
		
		Station minEndStation = curDis.getRetunableStationList().get(0);
		double minEndDis = Math.sqrt((minEndStation.getPos().getX() - endPoint.getX()) * (minEndStation.getPos().getX() - endPoint.getX()) + 
				(minEndStation.getPos().getY() - endPoint.getY())*(minEndStation.getPos().getY() - endPoint.getY()));
		
		for(Station s: givenTypeAvaNotPlusStations) {
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

