package testSenario;

import card.VlibreCard;
import card.VmaxCard;
import planning.Map;
import ride.Server;
import ride.User;
import station.Station;

/**
 * Rental of a bike
 * 1. a user (card holder or not) rent a bike of a given type from a given station at a given
 * moment in time
 * 2. the user returns the bike to another station after a given duration (expressed in
 * minutes).
 * 3. the user get charged for the corresponding amount (possibly 0) and possibly receive
 * a time-credit (possibly 0)
 * @author SUNXIAOYU
 *
 */
public class UseCase {
	public static void main(String [] args) throws Exception {
		//###########¡°Setting up of myVelib¡±###########		
		Map map = Map.getInstance();
		map.init();
//		System.out.println(map);

		//###########¡°Rental of a bike¡±###########
		Server server = Server.getInstance();
		User Bob = new User();
		server.addUser(Bob);
		Bob.addCard(new VmaxCard(Bob));
		
		Station s1 = map.getStations().get(1);
		Station s5 = map.getStations().get(5);
		Station s6 = map.getStations().get(6);
		
		//Bob went to s, the source station
		//Bob pick up a bike at random
		//Bob identifies himself at the terminal and successfully picked the bike and rides away.

		server.rent(Bob,s6); 
		// Bob just rent a bike b form the station s. All these 3 objects needs to change their status together. 
		// In the means time the server generate a new Ride object to record this event.

		//Bob is riding the bike.
		//Bob arrived at station d
		server.restore(Bob,s5);
		// Bob just rent a bike b form the station d. All these 3 objects needs to change their status together. 
		// In the means time the server generate a new Record object to record this event.

		User Alix = new User();
		server.addUser(Alix);
		Alix.addCard(new VlibreCard(Alix));
		
		s6.setPlus(true);
		Server.log(s1.toString());
		Server.log(s6.toString());
		for (int i=0;i<5;i++) {
			server.rent(Alix, s1);
			server.restoreAfter(Alix, s6, 125);
		}

		Server.log(s1.toString());
		Server.log(s6.toString());		
	}
}
