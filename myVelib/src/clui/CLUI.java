package clui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

import card.VlibreCard;
import card.VmaxCard;
import planning.CurrentDistribution;
import planning.Map;
import ride.Server;
import ride.User;
import station.MostUsedComparator;
import station.Station;

/**
 * ClUI with basic useful orders.
 * @author Zhihao Li
 * 
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
	 * @param args not yet useful
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
		String command = inputForParsing[0];
		String[] args = Arrays.copyOfRange(inputForParsing, 1, inputForParsing.length);
		
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
		case "runtest":
			runtest(args);
			break;
		case "help":
			help();
			break;
		default:
			Server.error(INVALID_COMMAND);
		}
	}
	
	private static void help() {
		Server.log("setup <velibnetworkName>: to create a myVelib network with given name and\r\n" + 
				"consisting of 10 stations each of which has 10 parking slots and such that stations\r\n" + 
				"are arranged on a square grid whose of side 4km and initially populated with a 75%\r\n" + 
				"bikes randomly distributed over the 10 stations\r\n\n" + 
				"setup <name> <nstations> <nslots> <s> <nbikes>: to create a myVelib net-\r\n" + 
				"work with given name and consisting of nstations stations each of which has nslots\r\n" + 
				"parking slots and such that stations are arranged in as uniform as possible manner\r\n" + 
				"over an area you may assume either being circular of radium s or squared of side s\r\n" + 
				"(please document what kind of area your implementation of this command takes into\r\n" + 
				"account and how stations are distributed over it).Furthermore the network should\r\n" + 
				"be initially populated with a nbikes bikes randomly distributed over the nstations\r\n" + 
				"stations\r\n\n" + 
				"addUser <userName,cardType, velibnetworkName> : to add a user with name\r\n" + 
				"userName and card cardType (i.e. ''none'' if the user has no card) to a myVelib net-\r\n" + 
				"work velibnetworkName\r\n\n" + 
				"offline <velibnetworkName, stationID> : to put oine the station stationID\r\n" + 
				"of the myVelib network velibnetworkName\r\n\n" + 
				"online <velibnetworkName, stationID> : to put online the station stationID of\r\n" + 
				"the myVelib network velibnetworkName\r\n\n" + 
				"rentBike <userID, stationID> : to let the user userID renting a bike from station\r\n" + 
				"stationID (if no bikes are available should behave accordingly)\r\n\n" + 
				"returnBike <userID, stationID, time> : to let the user userID returning a bike\r\n" + 
				"to station stationID at a given instant of time time (if no parking bay is available\r\n" + 
				"should behave accordingly). This command should display the cost of the rent\r\n\n" + 
				"displayStation<velibnetworkName, stationID> : to display the statistics (as of\r\n" + 
				"Section 2.4) of station stationID of a myVelib network velibnetwork.\r\n" + 
				"displayUser<velibnetworkName, userID> : to display the statistics (as of Sec-\r\n" + 
				"tion 2.4) of user userID of a myVelib network velibnetwork.\r\n\n" + 
				"sortStation<velibnetworkName, sortpolicy> : to display the stations in increas-\r\n" + 
				"ing order w.r.t. to the sorting policy (as of Section 2.4) of user sortpolicy.\r\n\n" + 
				"display <velibnetworkName>: to display the entire status (stations, parking bays,\r\n" + 
				"users) of an a myVelib network velibnetworkName.\n");
		
	}
	
	/**
	 * run test scenarios from file
	 * @param args filepath
	 */
	private static void runtest(String[] args) {
		if (args.length != 1) {
			Server.error(PARA_NB_NOT_MATCH);
			return;
		}
		FileReader file = null;
		BufferedReader reader = null;
//		
		System.out.println("Working Directory = " + System.getProperty("user.dir"));
		
		try {
			file = new FileReader("./myVelib/src/eval/" + args[0]);
			reader = new BufferedReader(file);
			String line = "";
			
			System.out.println("Reading scenario file " + args[0]);

			while ((line = reader.readLine()) != null) { // read the file line by line
				// pass line to CLUIThread.parseUserInput
				// ignores empty lines or lines starting with #
				if (line.length() > 0 && !line.substring(0, 1).equals("#")) parseAndDo(line);
			}
			Server.log("Scenario completed!");
		} catch (FileNotFoundException e) {
			System.out.print("File not found. If scenario file is in eval folder, the filepath should be src/eval/filename> \n");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (reader != null) {
				try {reader.close();}
				catch (IOException e) {// Ignore issues 
				}
			}
			if (file != null) {
				try {file.close();}
				catch (IOException e) { // Ignore issues during closing g
				}
			}
		}
		
	}
	/**
	 * to display the whole status of map
	 */
	private static void displayAll() {
		Server.log("");
		Server.log("*************DISPLAYING THE SYSTEM*****************");
		Server.log(Map.getInstance().toString());
		Server.log("");
	}
	/**
	 * to sort stations by most used consequence
	 * @param args 
	 */
	private static void sortStation(String[] args) {
		MostUsedComparator mostUsedComp = new MostUsedComparator();
		ArrayList<Station> StationList = new ArrayList<Station>(Map.getInstance().getStations().values());
		Collections.sort(StationList,mostUsedComp);
		Server.log("*************BEGIN SORTING*****************");
		for (Station s:StationList) {
			s.displayStat();
		}
		Server.log("*************END SORTING*****************");
	}
	/**
	 * to display user information
	 * @param args
	 */
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
	/**
	 * to display station condition
	 * @param args userName userId
	 */
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
	/**
	 * to return a bike into a station by somebody after a period of time
	 * @param args userID stationId time:
	 */
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
	 * to rent a bike from a station by someone(we can appoint a specific type of bike to rent)
	 * @param args userID stationID (bikeType(e or m))
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
				Server.log(String.format("User Id:%d StartStationID %d", user.getUserId(), station.getStationId()));
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
				Server.log(String.format("User Id:%d StartStationID %d", user.getUserId(), station.getStationId()));
			}catch(NumberFormatException e) {
				Server.error(INVALID_PARA);
			}catch(Exception e) {
				Server.error(ID_NOT_FOUND);
		}
	}
	}
	/**
	 * to set a station offline
	 * @param args vlibnetworkName stationID
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
	 * to set a station online
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
	 * to add a user into our system
	 * @param args userName cardType vlibnetworkName
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
	 * to set up a new system with several stations, slots, bikes and a given area side 
	 * @param args vlibnetworkName or (name nstations nslots sidearea nbikes)
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
						map.init(nstations,nslots,nbikes,s);
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
