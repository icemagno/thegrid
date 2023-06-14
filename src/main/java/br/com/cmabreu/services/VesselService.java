package br.com.cmabreu.services;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import br.com.cmabreu.pilot.AutoPilot;
import br.com.cmabreu.pilot.Ships;
import br.com.cmabreu.pilot.Vessel;
import br.com.cmabreu.pilot.observers.InfoObserver;
import br.com.cmabreu.pilot.observers.PilotObserver;

@Service
public class VesselService {
	private double interval = 1.0;
	private double boatspeed = 0.0;
	private double heading = 45.0;
	private double latitude = 47.65;
	private double longitude = -122.45;
	private double altitude = 0.0;
	private int rudderposition = 0;
	private int throttleposition = 0;
	
	@PostConstruct
	private void init() {
		InfoObserver infoObserver =  new InfoObserver();
		
		Vessel vessel = new Vessel( infoObserver , interval, Ships.FRAGATA_NITEROI, boatspeed, heading, 
				latitude, longitude, altitude, rudderposition, throttleposition, 2.0);
		vessel.start();
		
		vessel.setThrottlePosition(12);
		
		AutoPilot ap = new AutoPilot( vessel );
		PilotObserver pilotObs = new PilotObserver();
		ap.setObserver( pilotObs );
		ap.start();
		
		ap.setCourseTo( 180 );		
	}
	
}
