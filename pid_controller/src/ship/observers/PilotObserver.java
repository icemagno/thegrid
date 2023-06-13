package ship.observers;

public interface PilotObserver {
	public void log( int rudderPosition, double currentAzimuth, double targetAzimuth, double error );
}
