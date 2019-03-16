package myVelib;

public class UseCase1 {	
	public static void main(String [] args) {
		Map map = Map.getInstance();
//		System.out.println(map);
		map.init();		
		System.out.println(map);
		
	}
}
