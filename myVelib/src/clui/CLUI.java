package clui;

import java.util.Arrays;
import java.util.Scanner;

import planning.Map;
import ride.Server;

public class CLUI {
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
		String[] parameters = Arrays.copyOfRange(inputForParsing, 1, inputForParsing.length);
		System.out.println(Arrays.toString(parameters));
		
		Server server = Server.getInstance();
		Map map = Map.getInstance();
		
		switch(command) {
		case "setup":
			setup(parameters);
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

	private static void setup(String[] parameters) {
		// TODO Auto-generated method stub
		
	}

}
