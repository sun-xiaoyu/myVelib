package station;

/**
 * observer pattern to notify users of availability of endStation
 * @author Zhihao Li
 *
 */
public interface Observer {
	public void update(Station station);
}
