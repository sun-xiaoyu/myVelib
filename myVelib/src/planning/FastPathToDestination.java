package planning;

import java.util.ArrayList;

import ride.Server;
import station.GPS;
import station.Station;
/**
 * policy: fast path to destination: the pickup station is chosen so that the combined
 * walk+ride delay to the destination point d is minimal whereas the return station is
 * chosen so that the walking distance to destination point d is minimal
 * @author Zhihao Li
 *
 */
public class FastPathToDestination implements PlanningAlgo{
	/**
	 * fast path to destination
	 */
	@Override
	public Answer handle(Request request) throws Exception {
		GPS startPoint = request.getStartPos();
		GPS endPoint = request.getEndPos();
		CurrentDistribution curDis = CurrentDistribution.getInstance();
		ArrayList<Station> givenTypeAvaStations = null;// whether this init is a correct choice in case that ArrayList can not be covered directly 
		double ridingSpeed = 0.00001;//if not given proper bicycle type, time cost would be extremely large
		if(request.getBikeType().equalsIgnoreCase("E")) {
			ridingSpeed = Server.eleRidingSpeed;
			givenTypeAvaStations = curDis.geteAvaStationList();
		}
		
		if(request.getBikeType().equalsIgnoreCase("M")) {
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
				(minStartStation.getPos().getY() - startPoint.getY()) * (minStartStation.getPos().getY() - startPoint.getY()))/
				Server.walkingSpeed +
				(Math.abs(minStartStation.getPos().getX() - endPoint.getX())+ 
						Math.abs(minStartStation.getPos().getY() - endPoint.getY()))/
				ridingSpeed;
		
		for(Station s: givenTypeAvaStations) {
			double walkRideTime = Math.sqrt((s.getPos().getX() - startPoint.getX()) * (s.getPos().getX() - startPoint.getX())+
					(s.getPos().getY() - startPoint.getY()) * (s.getPos().getY() - startPoint.getY()))/
					Server.walkingSpeed +
					(Math.abs(s.getPos().getX() - endPoint.getX())+ 
							Math.abs(s.getPos().getY() - endPoint.getY()))/
					ridingSpeed;
			if( walkRideTime < minWalkRideTime) {
				minStartStation = s;
				minWalkRideTime = walkRideTime; 
			}
		}			

		double minStartDis = Math.sqrt((minStartStation.getPos().getX() - startPoint.getX())*(minStartStation.getPos().getX() - startPoint.getX())
				+ (minStartStation.getPos().getY() - startPoint.getY())*(minStartStation.getPos().getY() - startPoint.getY()));
		double minRideDis = (Math.abs(minStartStation.getPos().getX() - minEndStation.getPos().getX())+ 
				Math.abs(minStartStation.getPos().getY() - minEndStation.getPos().getY()));
		double minTime = (minEndDis + minStartDis)/ Server.walkingSpeed + minRideDis/ridingSpeed;
		double minDis = minStartDis + minEndDis + minRideDis;
		if(minTime > 500) {
			throw new Exception("BikeType input illegal.");
		}
		
		Answer ans = new Answer(minStartStation, minEndStation, minTime, minDis);
		return ans;
	}
}
