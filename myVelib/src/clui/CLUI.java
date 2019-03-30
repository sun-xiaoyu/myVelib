package clui;

import java.util.Arrays;
import java.util.Scanner;

import card.VlibreCard;
import card.VmaxCard;
import planning.Map;
import ride.Server;
import ride.User;

public class CLUI {
	private static final String INCORRECT_NB_PARA = "The number of parameters is not correct.";
	private static final String INVALID_PARA =  "Invalid parameters";
	private static final String NETWORK_EXIST = "The network already exist, only one network at a time.";
	private static final String MAP_BUILT = "Map is built successfuly.";
	private static final String NETWORK_NOT_EXIST = "The input network does not exist.";
	private static final String USER_ADDED = "User added";
	
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

	private static void parseAndDo(String input) {
		String[] inputForParsing = input.split(" ");
		System.out.println(Arrays.toString(inputForParsing));
		String command = inputForParsing[0];
		String[] args = Arrays.copyOfRange(inputForParsing, 1, inputForParsing.length);
		System.out.println(Arrays.toString(args));
		
		Server server = Server.getInstance();
		Map map = Map.getInstance();
		System.out.println(map.getName());
		command = command.toLowerCase();
		switch(command) {
		case "setup":
			setup(args);
			break;
		case "adduser":
			addUser(args);
			break;
		case "offline":
			break;
		case "online":
			break;
		case "rentbike":
			break;
		case "returnbike":
			break;
		case "displaystation":
			break;
		case "displayuser":
			break;
		case "sortstation":
			break;
		case "display":
			break;		
		}
	}

	private static void addUser(String[] args) {
		if (args.length != 3) {
			Server.error(INCORRECT_NB_PARA);
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
		Server.log(USER_ADDED);
	}

	private static void setup(String[] args) {
		if (args.length != 1 && args.length != 5) {
			Server.error(INCORRECT_NB_PARA);
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
				int nstations, nslot, nbikes;
				double s;
				name = args[0];
				try {
					nstations = Integer.parseInt(args[1]);
					nslot = Integer.parseInt(args[2]);
					s = Double.parseDouble(args[3]);
					nbikes = Integer.parseInt(args[4]);
					map.setSizeX(s);
					map.setSizeY(s);
					try {
						map.init(nstations,nslot);
						map.setName(name);
						map.display();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//TODO deal with nbike;
				}catch (Exception NumberFormatException) {
					Server.error(INVALID_PARA);
				}
			}
			else Server.error(INVALID_PARA);
		}
	}

}
