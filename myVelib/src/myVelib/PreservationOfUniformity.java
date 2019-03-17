package myVelib;

import java.util.ArrayList;

public class PreservationOfUniformity implements PlanningAlgo{
	@Override
	public Answer handle(Request request) throws Exception {
		GPS startPoint = request.getStartPos();
		GPS endPoint = request.getEndPos();
		CurrentDistribution curDis = CurrentDistribution.getInstance();
		ArrayList<Station> givenTypeAvaStations = null;// whether this init is a correct choice in case that ArrayList can not be covered directly 
		ArrayList<Station> returnableStations = curDis.getRetunableStationList();
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
		
		Station minEndStation = returnableStations.get(0);
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
		
		for(Station s: returnableStations) {
			double endDis = Math.sqrt((s.getPos().getX() - endPoint.getX())*(s.getPos().getX() - endPoint.getX()) + 
					(s.getPos().getY() - endPoint.getY()) * (s.getPos().getY() - endPoint.getY()));
			if( endDis < minEndDis) {
				minEndStation = s;
				minEndDis = endDis; 
			}
		}

		/**
		 *  to get better start station
		 */
		Station minBetterStartStation = null;
		double minBetterStartDis = 999999;
		for(Station s:givenTypeAvaStations) {
			double betterStartDis = Math.sqrt((s.getPos().getX() - startPoint.getX()) * (s.getPos().getX() - startPoint.getX()) + 
					(s.getPos().getY() - startPoint.getY())*(s.getPos().getY() - startPoint.getY()));
			boolean flag1 = (betterStartDis < 1.05*minStartDis);
			boolean flag2 = false;
			if(request.getBikeType() == 'E') {
				if(s.getEBicycleNumber() > minStartStation.getEBicycleNumber());
				flag2 = true;
			}
			else if(request.getBikeType() == 'M') {
				if(s.getMBicycleNumber() > minStartStation.getMBicycleNumber());
				flag2 = true;
			}
			boolean flag3 = (betterStartDis < minBetterStartDis);
			if(flag1 && flag2 && flag3) {
				minBetterStartStation = s;
				minBetterStartDis = betterStartDis;
			}
		}
		if(minBetterStartStation != null) {
			minEndDis = minBetterStartDis;
			minEndStation = minBetterStartStation;
		}
		
		/**
		 *  to get better end station;
		 */
		Station minBetterEndStation = null;
		double minBetterEndDis =  999999;
		for(Station s:givenTypeAvaStations) {
			double betterEndDis = Math.sqrt((s.getPos().getX() - endPoint.getX()) * (s.getPos().getX() - endPoint.getX()) + 
					(s.getPos().getY() - endPoint.getY())*(s.getPos().getY() - endPoint.getY()));
			boolean flag1 = (betterEndDis < 1.05*minEndDis);
			boolean flag2 = (s.getSpareSlotNum() > minEndStation.getSpareSlotNum());
			boolean flag3 = (betterEndDis < minBetterEndDis);
			if(flag1 && flag2 && flag3) {
				minBetterEndStation = s;
				minBetterEndDis = betterEndDis;
			}
		}
		if(minBetterEndStation != null) {
			minEndDis = minBetterEndDis;
			minEndStation = minBetterEndStation;
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
