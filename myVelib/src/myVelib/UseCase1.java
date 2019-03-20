package myVelib;

public class UseCase1 {	
	public static void main(String [] args) throws Exception {
	Map map = Map.getInstance();
	//		System.out.println(map);
	map.init();		
	System.out.println(map);
	Server server = Server.getInstance();
	//###########“Simulation of a planning ride”###########
	//假定我们只有一个服务器Suppose that we have only one server. It's better to set the class server to static. Otherwise:
	User Alice = new User("Alice");
	server.addUser(Alice);
	Alice.addCard(new VlibreCard(Alice));
	// Now we recieve a request from User Alice.
	
	GPS moulon = new GPS();
	GPS massy = new GPS();
	Request rq = new Request(Alice, moulon, massy, "e", "MWD"); 
	

	/**
	 * User didn't indicate the ride planning policy.Server
	 * this solver uses the default ride planning policy.
	 */
	 
	Solution solution1 = server.handle(rq);
	
	/**
	 * server send Alice with the soluton s
	 * Alice wish to change to the “fastest” policy
	 */
	
	Alice.setPolicy("FPTD");
	//rq = Alice.send(); // no parameter means send the former request, but with her newly chosen policy.
	Solution solution2 = server.handle(rq);
	// Server.send(Alice, s2);
	
	//The client  Alice recieve the information about the ride accepct s1. She then clicks “start”.
	// Alice.accept(s1);
	Server.addPlannedRide(solution1);
	
	Station startStaion,endStation;
	startStaion = solution1.getStartStation();
	endStation = solution1.getEndStation();
	//Alice went to s, the source station as indicated in s1
	//Alice pick up a bike at random
	//Alice identifies herself at the terminal and successfully picked the bike and rides away.
	
	server.rent(Alice,startStaion); // Alice just rent a bike b form the station s. All these 3 objects needs to change their status together.
	//In this method, the server searched in the list of planned ride of which the user=Alice. The server confirm that Alice is on her way and changed this the “status” field of this Ride object form Planning to an Ongoing.
	
	//Alice is riding the bike.
	//Station d became inavailble.
	endStation.setOffline(false);;// This method triggers the observation pattern.
	// TODO
	//========未完待续========
	}
}
