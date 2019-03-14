package myVelib;

public class VlibreCardVisitor implements CardVisitor {

	@Override
	public double visit(EBike ebike, Ride ride) {
		double duration = ride.getLengthInMin();
		int credit = ride.getUser().getCard().getTimeCredit();
		if (duration >60 && credit != 0) {
			if (duration - credit > 60) {
				duration = duration - credit;
			}else {
				duration = 60;
				ride.getUser().getCard().setTimeCredit(credit - ((int)duration - 60));
			}
		}
		if (duration < 60.005) return duration / 60 * 1;
		return (duration-60)/60*2 + 1;
		
		
		
		/*
		if (duration >60 && credit != 0) {
			if (duration - credit > 60) {
				duration = duration - credit;
			}else {
				duration = 60;
				user.ride.getUser().getCard().setTimeCredit(duration - 60);
			}
			duration = 
		}
		return duration/60*1;
		*/
	}

	@Override
	public double visit(MBike mbike, Ride ride) {
		double duration = ride.getLengthInMin();
		int credit = ride.getUser().getCard().getTimeCredit();
		if (duration >60 && credit != 0) {
			if (duration - credit > 60) {
				duration = duration - credit;
			}else {
				duration = 60;
				ride.getUser().getCard().setTimeCredit(credit - ((int)duration - 60));
			}
		}
		if (duration < 60.005) return 0;
		return (duration-60)/60*1 + 0;
	}

}
