package clui;

import java.util.Arrays;
import java.util.Scanner;

import planning.Map;
import ride.Server;

public class CLUI {
	private static final String INCORRECT_NB_PARA = "The number of parameters is not correct.";
	private static final String INVALID_PARA =  "Invalid parameters";
	
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
		
		switch(command) {
		case "setup":
			setup(args);
			break;
		case "addUser":
			break;
		case "offline":
			break;
		case "online":
			break;
		case "rentBike":
			break;
		case "returnBike":
			break;
		case "displayStation":
			break;
		case "displayUser":
			break;
		case "sortStation":
			break;
		case "display":
			break;		
		}
	}

	private static void setup(String[] args) {
		if (args.length != 1 && args.length != 5) Server.log(INCORRECT_NB_PARA);
		if (args.length == 1) {
			if (args[0] != "") {
				Map map = Map.getInstance();
				map.init();
				map.setName(args[0]);
				System.out.println(map);
			}
			else Server.log(INVALID_PARA);
		}
		if (args.length == 5){
			if (args[0] != "") {
				Map map = Map.getInstance();
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
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					map.setName(args[0]);
					//TODO deal with nbike;
				}catch (Exception NumberFormatException) {
					Server.error(INVALID_PARA);
				}
				
			}
			else Server.log(INVALID_PARA);
		}
	}

}
