package test;

import ship.AutoPilot;
import ship.Ships;
import ship.Vessel;
import ship.observers.BasicPilotObserver;
import ship.observers.ObserverFactory;
import ship.observers.PilotObserver;

public class Main {
	
	static double interval = 1.0;
	static double boatspeed = 0.0;
	static double heading = 45.0;
	static double latitude = 47.65;
	static double longitude = -122.45;
	static double altitude = 0.0;
	static int rudderposition = 0;
	static int throttleposition = 0;
	
	public static void main(String[] args) {
		
		Vessel vessel = new Vessel( ObserverFactory.getObserver() , interval, Ships.FRAGATA_NITEROI, boatspeed, heading, 
				latitude, longitude, altitude, rudderposition, throttleposition, 2.0);
		vessel.start();
		
		vessel.SetThrottlePosition(12);
		
		AutoPilot ap = new AutoPilot( vessel, vessel.GetHeading() );
		PilotObserver pilotObs = new BasicPilotObserver();
		ap.setObserver( pilotObs );
		ap.start();
		
		ap.setCourseTo( 180 );

	}

}
