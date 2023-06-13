package ship.observers;

public class ObserverFactory {
	
	public static InfoObserver getObserver() {
		return new BasicInfoObserver();
	}

}
