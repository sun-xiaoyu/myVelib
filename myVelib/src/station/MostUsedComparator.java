package station;

import java.util.Comparator;

public class MostUsedComparator implements Comparator<Station> {

	@Override
	public int compare(Station s1, Station s2) {
		return (s1.getTotalRent()+s1.getTotalReturn()) - (s2.getTotalRent()+s2.getTotalReturn());
	}
	
}
