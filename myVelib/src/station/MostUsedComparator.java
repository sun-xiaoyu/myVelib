package station;

import java.util.Comparator;

/**
 * comparator: with it we can sort stations to list the most used station on the top.
 * @author SUNXIAOYU
 *
 */
public class MostUsedComparator implements Comparator<Station> {

	@Override
	public int compare(Station s1, Station s2) {
		return -((s1.getTotalRent()+s1.getTotalReturn()) - (s2.getTotalRent()+s2.getTotalReturn()));
	}
	
}
