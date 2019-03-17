package myVelib;

public class UseCase {
	public static void main(String [] args) {
		//###########“Setting up of myVelib”###########
		Server ser = new Server();
		ser.importUserFromFile("initialUser.dat");
		ser.importStationFromFile("initialStation.dat");
		ser.addBike(500,'m');
		ser.addBike(500,'e');
		ser.randomConfig();
		// or simply
		ser.initFromFile();
		
		Map map = new Map(1,2);
		Map.getMap()

		//###########“Rental of a bike”###########
		Server server = new server();
		User Bob= new User();
		sys.addUser(Bob);
		Bob.addCard("Vmax");

		//Bob went to s, the source station as indicated in s1
		//Bob pick up a bike at random
		Bike b = random.choice(s.getAvailbleBike());
		//Bob identifies herself at the terminal and successfully picked the bike and rides away.

		Server.rent(Bob,b,s); 
		// Bob just rent a bike b form the station s. All these 3 objects needs to change their status together. In the means time the server generate a new Ride object to record this event.

		//Bob is riding the bike.
		//Bob arrived at station d
		Server.restore(Bob,b,d);
		// Bob just rent a bike b form the station d. All these 3 objects needs to change their status together. In the means time the server generate a new Record object to record this event.

		//###########“Simulation of a planning ride”###########
		//假定我们只有一个服务器Suppose that we have only one server. It's better to set the class server to static. Otherwise:
		Server server = new server();
		User Alice = new User();
		sys.addUser(Alice);
		Alice.addCard("Vmax");
		// Now we recieve a request from User Alice.
		Request rq = new Request(Alice, moulon, massy, 'e', new shortest()); 
		//or rq = Alice.send(rq);
		//User didn't indicate the ride planning policy.Server
		//this solver uses the default ride planning policy.
		Solution s1 = .handle(rq)
		Server.send(Alice, s1);
		//solver = null;

		//Alice wish to change to the “fastest” policy
		Alice.setPolicy(“fastest”);
		//rq = Alice.send(); // no parameter means send the former request, but with her newly chosen policy.
		Solution s2 = Server.handle(rq)
		Server.send(Alice, s2);

		//The client  Alice recieve the information about the ride accepct s1. She then clicks “start”.
		Alice.accept(s1);
		Server.addPlannedRide(s1);

		Station s,d;
		s = s1.getSource();
		d = s1.getDest();
		//Alice went to s, the source station as indicated in s1
		//Alice pick up a bike at random
		Bike b = random.choice(s.getAvailbleBike());
		//Alice identifies herself at the terminal and successfully picked the bike and rides away.

		Server.rent(Alice,b,s); // Alice just rent a bike b form the station s. All these 3 objects needs to change their status together.
		//In this method, the server searched in the list of planned ride of which the user=Alice. The server confirm that Alice is on her way and changed this the “status” field of this Ride object form Planning to an Ongoing.

		//Alice is riding the bike.
		//Station d became inavailble.
		d.setAvailbilty(False);
		d.report();// This method triggers the observation pattern. In this method, a "Server.notify();" is called.
		//========未完待续========

	}
}
