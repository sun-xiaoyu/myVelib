package station;
/**
 * observable for end stations' condition so that users can be notified
 * @author Zhihao Li
 *
 */
public interface Observable {
	public void registerObserver(Observer observer);
	public void removeObserver(Observer observer);
	public void notifyObservers();
}
