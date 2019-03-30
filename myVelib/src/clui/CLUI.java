package clui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

import card.VlibreCard;
import card.VmaxCard;
import planning.Map;
import ride.Server;
import ride.User;
import station.MostUsedComparator;
import station.Station;

/**
 * 
 * @author Lenovo
 * ClUI with basic useful orders.
 */
public class CLUI {
	private static final String PARA_NB_NOT_MATCH = "The number of parameters is not correct.";
	private static final String INVALID_PARA =  "Invalid parameters.";
	private static final String NETWORK_EXIST = "The network already exist, only one network at a time.";
	private static final String MAP_BUILT = "Map is built successfuly.";
	private static final String NETWORK_NOT_EXIST = "The input network does not exist.";
	private static final String USER_ADDED = "User added, ID:";
	private static final String INVALID_COMMAND = "Invalid command.";
	private static final String STATION_OFFLINE = "Station offline, ID:";
	private static final String STATION_ONLINE = "Station online, ID:";
	private static final String ID_NOT_FOUND = "ID not found";
	
	/**
	 * get into order receiver
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Welcome to myVelib system. type \"help\" for help.");
		Scanner reader = new Scanner(System.in); // Reading from keyboard
		while (true) {
			System.out.print("> ");
			String input = reader.nextLine();
			if (input.equalsIgnoreCase("EXIT")) {
				System.out.print("Thank you for using myVelib system. See you next time!");
				break;
			}
			parseAndDo(input);
		}
		reader.close();
	}
	/**
	 * receive input from system at any time
	 * @param input receive input from system
	 */
	private static void parseAndDo(String input) {
		String[] inputForParsing = input.split(" ");
		System.out.println(Arrays.toString(inputForParsing));
		String command = inputForParsing[0];
		String[] args = Arrays.copyOfRange(inputForParsing, 1, inputForParsing.length);
		System.out.println(Arrays.toString(args));
		
		command = command.toLowerCase();
		switch(command) {
		case "setup":
			setup(args);
			break;
		case "adduser":
			addUser(args);
			break;
		case "offline":
			offline(args);
			break;
		case "online":
			online(args);
			break;
		case "rentbike":
			rentBike(args);
			break;
		case "returnbike":
			returnBike(args);
			break;
		case "displaystation":
			displayStation(args);
			break;
		case "displayuser":
			displayUser(args);
			break;
		case "sortstation":
			sortStation(args);
			break;
		case "display":
			displayAll();
			break;	
		default:
			Server.error(INVALID_COMMAND);
		}
	}

	private static void displayAll() {
		Server.log(Map.getInstance().toString());
	}

	private static void sortStation(String[] args) {
		MostUsedComparator mostUsedComp = new MostUsedComparator();
		ArrayList<Station> StationList = new ArrayList<Station>(Map.getInstance().getStations().values());
		Collections.sort(StationList,mostUsedComp);
		for (Station s:StationList) {
			s.displayStat();
		}
	}

	private static void displayUser(String[] args) {
		if (args.length != 2) {
			Server.error(PARA_NB_NOT_MATCH);
			return;
		}
		if (!args[0].equals(Map.getInstance().getName())) {
			Server.error(NETWORK_NOT_EXIST);
			return;
		}
		try{
			int userID = Integer.parseInt(args[1]);
			User user = Server.getInstance().getUsers().get(userID);
			user.displayStat();
		}catch(NumberFormatException e) {
			Server.error(INVALID_PARA);
		}catch(Exception e) {
			Server.error(ID_NOT_FOUND);
		}
	}

	private static void displayStation(String[] args) {
		if (args.length != 2) {
			Server.error(PARA_NB_NOT_MATCH);
			return;
		}
		if (!args[0].equals(Map.getInstance().getName())) {
			Server.error(NETWORK_NOT_EXIST);
			return;
		}
		try{
			int stationID = Integer.parseInt(args[1]);
			Station station = Map.getInstance().getStations().get(stationID);
			station.displayStat();
		}catch(NumberFormatException e) {
			Server.error(INVALID_PARA);
		}catch(Exception e) {
			Server.error(ID_NOT_FOUND);
		}
	}

	private static void returnBike(String[] args) {
		if (args.length != 3) {
			Server.error(PARA_NB_NOT_MATCH);
			return;
		}	
		try{
			int userID = Integer.parseInt(args[0]);
			int stationID = Integer.parseInt(args[1]);
			double time = Double.parseDouble(args[2]);
			Station station = Map.getInstance().getStations().get(stationID);
			User user = Server.getInstance().getUsers().get(userID);
			Server.getInstance().restoreAfter(user,station,time); 
		}catch(NumberFormatException e) {
			Server.error(INVALID_PARA);
		}catch(Exception e) {
			Server.error(ID_NOT_FOUND);
		}
		
	}

	/**
	 * 
	 * @param args UserID, stationID, bikeType(e or m)
	 */
	private static void rentBike(String[] args) {
		if ((args.length != 2) && (args.length != 3)) {
			Server.error(PARA_NB_NOT_MATCH);
			return;
		}
		if (args.length == 2) {
			try{
				int userID = Integer.parseInt(args[0]);
				int stationID = Integer.parseInt(args[1]);
				Station station = Map.getInstance().getStations().get(stationID);
				User user = Server.getInstance().getUsers().get(userID);
				Server.getInstance().rent(user,station); 
				Server.log("");
			}catch(NumberFormatException e) {
				Server.error(INVALID_PARA);
			}catch(Exception e) {
				Server.error(ID_NOT_FOUND);
			}
		}
		if (args.length == 3) {
			try{
				int userID = Integer.parseInt(args[0]);
				int stationID = Integer.parseInt(args[1]);
				char bikeType = args[2].charAt(0);
				Station station = Map.getInstance().getStations().get(stationID);
				User user = Server.getInstance().getUsers().get(userID);
				Server.getInstance().rent(user,station,bikeType); 
				Server.log("");
			}catch(NumberFormatException e) {
				Server.error(INVALID_PARA);
			}catch(Exception e) {
				Server.error(ID_NOT_FOUND);
		}
	}
	}
	/**
	 * 
	 * @param args vlibnetworkName, stationID
	 */
	private static void offline(String[] args) {
		if (args.length != 2) {
			Server.error(PARA_NB_NOT_MATCH);
			return;
		}
		if (!args[0].equals(Map.getInstance().getName())) {
			Server.error(NETWORK_NOT_EXIST);
			return;
		}
		try{
			int stationID = Integer.parseInt(args[1]);
			Map.getInstance().getStations().get(stationID).setOffline(true);
			Server.log(STATION_OFFLINE+stationID);
		}catch(NumberFormatException e) {
			Server.error(INVALID_PARA);
		}catch(Exception e) {
			Server.error(ID_NOT_FOUND);
		}
	}
	/**
	 * 
	 * @param args vlibnetworkName, stationID
	 */
	private static void online(String[] args) {
		if (args.length != 2) {
			Server.error(PARA_NB_NOT_MATCH);
			return;
		}
		if (!args[0].equals(Map.getInstance().getName())) {
			Server.error(NETWORK_NOT_EXIST);
			return;
		}
		try{
			int stationID = Integer.parseInt(args[1]);
			Map.getInstance().getStations().get(stationID).setOffline(false);
			Server.log(STATION_ONLINE+stationID);
		}catch(NumberFormatException e) {
			Server.error(INVALID_PARA);
		}catch(Exception e) {
			Server.error(ID_NOT_FOUND);
		}		
	}

	/**
	 * 
	 * @param args userName, cardType, vlibnetworkName
	 */
	private static void addUser(String[] args) {
		if (args.length != 3) {
			Server.error(PARA_NB_NOT_MATCH);
			return;
		}
		if (!args[2].equals(Map.getInstance().getName())) {
			Server.error(NETWORK_NOT_EXIST);
			return;
		}
		args[1] = args[1].toLowerCase(); 
		Server server = Server.getInstance();
		User u;
		switch (args[1]) {
		case "vlibre":
			u = new User(args[0]);
			u.addCard(new VlibreCard(u));
			server.addUser(u);
			break;
		case "vmax":
			u = new User(args[0]);
			u.addCard(new VmaxCard(u));
			server.addUser(u);
			break;
		case "none":
			u = new User(args[0]);
			server.addUser(u);
			break;
		default:
			Server.error(INVALID_PARA);	
			return;
		}
		Server.log(USER_ADDED + u.getUserId());
	}

	/**
	 * 
	 * @param args vlibnetworkName
	 * @param args name, nstations, nslots, sidearea, nbikes
	 */
	private static void setup(String[] args) {
		if (args.length != 1 && args.length != 5) {
			Server.error(PARA_NB_NOT_MATCH);
			return;
		}
		Map map = Map.getInstance();
		if (map.getName()!=null) {
			Server.error(NETWORK_EXIST);
			return;
		}
		if (args.length == 1) {
			if (args[0] != "") {
				map.init();
				map.setName(args[0]);
				Server.log(MAP_BUILT);
				map.display();
			}
			else Server.error(INVALID_PARA);
		}
		if (args.length == 5){
			if (args[0] != "") {
				String name;
				int nstations, nslots, nbikes;
				double s;
				name = args[0];
				try {
					nstations = Integer.parseInt(args[1]);
					nslots = Integer.parseInt(args[2]);
					s = Double.parseDouble(args[3]);
					nbikes = Integer.parseInt(args[4]);
					map.setSizeX(s);
					map.setSizeY(s);
					try {
						map.init(nstations,nslots,nbikes);
						map.setName(name);
						map.display();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}catch (Exception NumberFormatException) {
					Server.error(INVALID_PARA);
				}
			}
			else Server.error(INVALID_PARA);
		}
	}

}
