package planning;

import java.util.ArrayList;

import ride.Server;
import station.GPS;
import station.Station;
/**
 * policy: avoid plus station: like minimal walking distance but return station cannot be
 * a ¡°plus¡± station
 * @author Zhihao Li
 *
 */
public class AvoidPlusStation implements PlanningAlgo{
	/**
	 * Avoid Plus Station
	 */
	@Override
	public Answer handle(Request request) throws Exception {
		GPS startPoint = request.getStartPos();
		GPS endPoint = request.getEndPos();
		CurrentDistribution curDis = CurrentDistribution.getInstance();
		ArrayList<Station> givenTypeAvaStations = null;// whether this init is a correct choice in case that ArrayList can not be covered directly 
		ArrayList<Station> notPlusReturnableStation = curDis.getRetunableStationList();
		double ridingSpeed = 0.00001;//if not given proper bicycle type, time cost would be extremely large
		if(request.getBikeType().equalsIgnoreCase("E")) {
			ridingSpeed = Server.eleRidingSpeed;
			givenTypeAvaStations = curDis.geteAvaStationList();
		}
		
		if(request.getBikeType().equalsIgnoreCase("M")) {
			ridingSpeed = Server.mecRidingSpeed;
			givenTypeAvaStations = curDis.getmAvaStationList();
		}

		for(Station station: givenTypeAvaStations) {
			if(station.isPlus()) {
				givenTypeAvaStations.remove(station);
			}
		}
		
		if(givenTypeAvaStations.size() == 0) {
			Server.error("no available station containing given type of bicycle");
		}
		
		for(Station s: notPlusReturnableStation) {
			if(s.isPlus()) {
				notPlusReturnableStation.remove(s);
			}
		}
			
		
		Station minStartStation = givenTypeAvaStations.get(0);
		double minStartDis = Math.sqrt((minStartStation.getPos().getX() - startPoint.getX()) * (minStartStation.getPos().getX() - startPoint.getX()) + 
				(minStartStation.getPos().getY() - startPoint.getY())*(minStartStation.getPos().getY() - startPoint.getY()));
		
		Station minEndStation = notPlusReturnableStation.get(0);
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
		
		for(Station s: notPlusReturnableStation) {
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

