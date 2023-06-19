package br.com.cmabreu.services;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cmabreu.pilot.Airplane;

@Service
public class AirplaneService  {
	private Map<String, Airplane> airplanes;
	private final int SIMULATION_SPEED_DELAY = 50; // Delay interval between cicles
	@Autowired private CommunicatorService comm;

	public void spawn( double lon, double lat, long throttle ) {
		Airplane airplane = new Airplane( lat, lon, comm, SIMULATION_SPEED_DELAY );
		airplane.setThrottle( throttle );
		airplane.start();		
		
		this.airplanes.put( airplane.getUuid(), airplane );

		System.out.println( airplane.getUuid() );
	}
	
	
	@PostConstruct
	private void init() {
		this.airplanes = new HashMap<String, Airplane>();

		spawn( -43.1388, -22.9001, 50 );
	}
	
	public void setThrottle( long speed, String uuid ) {
		Airplane v = this.airplanes.get( uuid );
		if( v != null ) {
			v.setThrottle(speed);
		}		
	}


	public void setHeading(Long heading, String uuid) {
		Airplane v = this.airplanes.get( uuid );
		if( v != null ) {
			v.setCourseTo( heading );
		}	
	}

	public void setRudderPid(Double p, Double i, Double d, String uuid) {
		Airplane v = this.airplanes.get( uuid );
		if( v != null ) {
			v.setRudderPid(p, i, d);
		}	
	}

	public void setSimulationSpeed(Integer t, String uuid) {
		Airplane v = this.airplanes.get( uuid );
		if( v != null ) {
			v.setSimulationSpeed(t);
		}		
	}

	public void turnLeft(Long rDegree, String uuid) {
		if( rDegree > 15 ) return;
		Airplane v = this.airplanes.get( uuid );
		if( v != null ) {
			double currentCourse = v.getHeading();
			v.setCourseTo( currentCourse - rDegree );
		}
	}

	public void turnRight(Long rDegree, String uuid) {
		if( rDegree > 15 ) return;
		Airplane v = this.airplanes.get( uuid );
		if( v != null ) {
			double currentCourse = v.getHeading();
			v.setCourseTo( currentCourse + rDegree );
		}
	}


	public void setAltitude(Long altitude, String uuid) {
		Airplane v = this.airplanes.get( uuid );
		if( v != null ) {
			v.setAltitude(altitude);
		}		
	}


	public void setElevatorPid(Double p, Double i, Double d, String uuid) {
		Airplane v = this.airplanes.get( uuid );
		if( v != null ) {
			v.setElevatorPid(p, i, d);
		}	
	}


	public void up(Long meters, String uuid) {
		if( meters > 500 ) return;
		Airplane v = this.airplanes.get( uuid );
		if( v != null ) {
			double currentAltitude = v.getAltitude();
			v.setAltitude( currentAltitude + meters );
		}
	}

	public void down(Long meters, String uuid) {
		if( meters > 500 ) return;
		Airplane v = this.airplanes.get( uuid );
		if( v != null ) {
			double currentAltitude = v.getAltitude();
			double newAltitude = currentAltitude - meters;
			if( newAltitude > 5 ) v.setAltitude( newAltitude );
		}
	}
	
}
