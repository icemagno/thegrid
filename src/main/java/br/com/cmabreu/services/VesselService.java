package br.com.cmabreu.services;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cmabreu.pilot.IVesselObserver;
import br.com.cmabreu.pilot.InfoProtocol;
import br.com.cmabreu.pilot.Vessel;

@Service
public class VesselService implements IVesselObserver {
	private Map<String, Vessel> vessels;

	@Autowired private CommunicatorService comm;

	@PostConstruct
	private void init() {
		this.vessels = new HashMap<String, Vessel>();
		Vessel vessel = new Vessel( this , -22.9001, -43.1388, 1.0 );
		this.vessels.put( vessel.getUuid(), vessel );
		vessel.setThrottlePosition( 50 );
		vessel.start();		
		System.out.println( vessel.getUuid() );
	}
	
	public void setSpeed( long speed, String uuid ) {
		Vessel v = this.vessels.get( uuid );
		if( v != null ) {
			v.setThrottlePosition(speed);
		}		
	}


	@Override
	public synchronized void send(InfoProtocol info) {
		Vessel v = this.vessels.get( info.getUuid() );
		if( v != null ) {
			info.setCurrentAzimuth( v.getHeading() );
			info.setError( v.getError() );
			info.setTargetAzimuth( v.getTargetAzimuth() );
			info.setRudderPosition( v.getRudderPosition() );
			JSONObject payload = new JSONObject( info );
			try {
				comm.broadcastData("main_channel", payload);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}		
		
	}

	public void setHeading(Long heading, String uuid) {
		Vessel v = this.vessels.get( uuid );
		if( v != null ) {
			v.setCourseTo( heading );
		}	
	}

	public void setPid(Double p, Double i, Double d, String uuid) {
		Vessel v = this.vessels.get( uuid );
		if( v != null ) {
			v.setPid(p, i, d);
		}	
	}

	public void setTimeSpeed(Integer t, String uuid) {
		Vessel v = this.vessels.get( uuid );
		if( v != null ) {
			v.setInterval(t);
		}		
	}
	
}
