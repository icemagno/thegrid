/**
 * Main Simulation thread. Runs a loop that recalculates simulation parameters periodically. 
 * The simulation simulationSpeed can be set when the thread is started.
 */
package br.com.cmabreu.pilot;

import java.util.Random;
import java.util.UUID;

import org.json.JSONObject;

import br.com.cmabreu.misc.CommandSource;
import br.com.cmabreu.radar.Radar;
import br.com.cmabreu.services.CommunicatorService;
import ch.hsr.geohash.GeoHash;

/**
 * @author Tony Mattheys
 *
 */
public class Airplane extends Thread {
	private CompassDeviation compassDeviation;
	private CompassDeclination compassDeclination;
	double relativeWindSpeed;
	private double headingTrue;
	private double headingMagnetic;
	private double trueWindAngle ;
	private double apparentWindSpeed;
	private double apparentWindAngle;
	private String latChar = "N";
	private String lonChar = "E";	
	private double altitude;
	private double rudderFactor;
	private int rudderPosition;
	private int elevatorPosition;
	private double throttlePosition;
	private int simulationSpeed;
	private double hullSpeed;
	private double speed;
	private double heading;
	private double latitude;
	private double longitude;
	private double displacement;
	private double trueWindDirection;
	private double trueWindSpeed;
	private double earthRadius = 3443.89849;
	private Random generator = new Random(System.nanoTime());
	private Rudder rudder = null;
	private Elevator elevator = null;
	private String uuid;
	private double targetAzimuth;
	private double rudderError;
	private double elevatorError;
	private CommunicatorService comm;
	private double targetAltitude;
	private CommandSource cms;
	private Radar radar;
	
	public int getSimulationSpeed() {
		return simulationSpeed;
	}	
	
	private double calcHullSpeed( int waterLineLengthMeters ) {
		double waterLineLengthFeet = waterLineLengthMeters * 3.2808;
		double hullSpeed = 1.34 * Math.sqrt( waterLineLengthFeet );
		return hullSpeed;
	}
		
	
	/**
	 * Initialize the Simulation
	 * @param uuid 
	 */
	public Airplane( double lat, double lon, CommunicatorService comm, int simulationSpeed, CommandSource cms, String uuid ) {
		if( uuid == null ) uuid = UUID.randomUUID().toString();
		this.comm = comm;
		this.cms = cms;
		this.rudderFactor = 2.0;
		this.uuid = uuid;
		this.simulationSpeed = simulationSpeed;
		this.hullSpeed = calcHullSpeed( 10 ); // 90 = ship length
		this.speed = 0.0;
		this.heading = 0.0;
		this.latitude = lat;
		this.longitude = lon;
		this.rudderPosition = 0;
		this.throttlePosition = 0;
		this.altitude = 5000;
		this.trueWindDirection = generator.nextDouble() * 360;
		this.trueWindSpeed = generator.nextDouble() * 20.0 + 5.0;
		compassDeviation = new CompassDeviation();
		compassDeclination = new CompassDeclination();	
		this.rudder = new Rudder( 0.2, 0.0009, 0.0009, this );
		rudder.start();
		this.elevator = new Elevator(0.5, 0.0001, 20.0, this );
		elevator.start();
		this.radar = new Radar( this );
	}
	
	public String getPositionHash() {
		return GeoHash.withCharacterPrecision( this.getLatitude(), this.getLongitude(), 7 ).toBase32();
	}
	
	public double getAltitude() {
		return altitude;
	}
	
	public void setAltitude(double targetAltitude) {
		this.targetAltitude = targetAltitude;
		this.elevator.setAltitudeTo(targetAltitude);
	}
	
	public synchronized void send(InfoProtocol info) {
		info.setPositionHash( this.getPositionHash() );
		info.setSensorArea( this.radar.getSensorArea() );
		info.setCurrentAzimuth( getHeading() );
		info.setRudderError( getRudderError() );
		info.setElevatorError( getElevatorError() );
		info.setTargetAzimuth( getTargetAzimuth() );
		info.setTargetAltitude( getTargetAltitude() );
		info.setRudderPosition( getRudderPosition() );
		info.setElevatorPosition( getElevatorPosition() );
		JSONObject payload = new JSONObject( info );
		try { comm.broadcastData("grid_airplane_data", payload); } catch (Exception e) {	
			// TODO: handle exception
		}
	}
	
	public double getTargetAltitude() {
		return targetAltitude;
	}
		
	public double getElevatorError() {
		return elevatorError;
	}
	
	public void setRudderError( double error ) {
		this.rudderError = error;
	}
	
	public double getRudderFactor() {
		return rudderFactor;
	}
	
	public double getTargetAzimuth() {
		return targetAzimuth;
	}
	
	public double getRudderError() {
		return rudderError;
	}
	
	public String getUuid() {
		return uuid;
	}
	
	public void setRudderPid( double p, double i, double d ) {
		rudder.setPid(p,i,d);
	}	

	public void setCourseTo(double heading) {
		this.targetAzimuth = heading;
		rudder.setCourseTo( heading );
	}
	
	public void setRudderFactor(double rudderFactor) {
		this.rudderFactor = rudderFactor;
	}
	
	public void setSimulationSpeed(int simulationSpeed) {
		this.simulationSpeed = simulationSpeed;
	}
	
	public void setRudderPosition(Double p) {
		rudderPosition = p.intValue();
	}
	
	public void setElevatorPosition(Double p) {
		elevatorPosition = p.intValue();
	}
	
	public int getElevatorPosition() {
		return elevatorPosition;
	}

	public int getRudderPosition() {
		return rudderPosition;
	}	

	public void setThrottle(double t) {
		throttlePosition = t;
	}

	public double getLongitude() {
		return longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public double getHeading() {
		return heading;
	}

	public double getSpeed() {
		return speed;
	}

	public double getHeadingMagnetic() {
		return headingMagnetic;
	}

	public double getTrueWindSpeed() {
		return trueWindSpeed;
	}

	public double getTrueWindAngle() {
		return trueWindAngle;
	}

	public double getApparentWindSpeed() {
		return apparentWindSpeed;
	}

	public double getApparentWindAngle() {
		return apparentWindAngle;
	}

	@Override
	public void run() {
		while (true) {
			if (elevatorPosition != 0 && speed != 0  ) {
				altitude = altitude + elevatorPosition;
				if( altitude < 5 ) altitude = 5;
			}
			
			/**
			 * Gradually allow the boat speed to converge on the target speed
			 * by adding half of the required delta in each iteration of the 
			 * simulation. Otherwise the speed changes instantly which is
			 * SO unrealistic.
			 */
			if (throttlePosition >= 0) {
				speed = speed + (((hullSpeed * throttlePosition / 100) - speed) / 2);
			}

			/**
			 * Turn the boat in the direction indicated by the rudder. We multiply the
			 * rudder slider value by a factor to make the turn quicker or slower. At
			 * he end we need to make sure we have not gone through 360 degrees in one
			 * direction or the other and correct as necessary.
			 */
			if (rudderPosition != 0 && speed != 0) {
				heading = heading + rudderPosition * rudderFactor;
			}
			if (heading < 0) {
				heading = heading + 360;
			}
			if (heading >= 360) {
				heading = heading - 360;
			}
			/**
			 * Add some instantaneous jitter to the wind speed and direction
			 * Wind gusts randomly as much as 20% above and below the base number
			 * Wind direction randomly shifts by as much as 20 degrees
			 * We always keep the basic wind speed and direction but insert some
			 * variation positive or negative on every simulation cycle.
			 */
			double TWS = trueWindSpeed + (generator.nextDouble() - 0.5) * trueWindSpeed / 20.0;
			double TWD = trueWindDirection + (generator.nextDouble() - 0.5) * 20.0;
			if (TWD >= 360) {
				TWD = TWD - 360;
			}
			if (TWD < 0) {
				TWD = TWD + 360;
			}
			trueWindSpeed = TWS;
			trueWindDirection = TWD;
			
			/**
			 * To find the lat/lon of a point on true course t, distance d from (p1,l1) all in RADIANS
			 * along a rhumbline (initial point cannot be a pole!):
			 * 
			 * This calculation assumes a spherical earth and is quite accurate for our purposes
			 * 
			 * Formula: φ2 = asin( sin(φ1)*cos(d/R) + cos(φ1)*sin(d/R)*cos(θ) )
			 * λ2 = λ1 + atan2( sin(θ)*sin(d/R)*cos(φ1), cos(d/R)−sin(φ1)*sin(φ2) )
			 * where φ is latitude (in radians)
			 * λ is longitude (in radians)
			 * θ is the bearing (in radians, clockwise from north)
			 * d is the distance travelled (say, nautical miles)
			 * R is the earth’s radius in same units as d (say, 3443.89849 nautical miles)
			 * (d/R is the angular distance, in radians)
			 * 
			 */
			displacement = speed * simulationSpeed / 3600;
			double p2 = Math.asin(Math.sin(Math.toRadians(latitude)) * Math.cos(displacement / earthRadius) + Math.cos(Math.toRadians(latitude)) * Math.sin(displacement / earthRadius) * Math.cos(Math.toRadians(heading)));
			latitude = Math.toDegrees(p2);
			double l2 = Math.toRadians(longitude) + Math.atan2(Math.sin(Math.toRadians(heading)) * Math.sin(displacement / earthRadius) * Math.cos(Math.toRadians(latitude)), Math.cos(displacement / earthRadius) - Math.sin(Math.toRadians(latitude)) * Math.sin(p2));
			longitude = Math.toDegrees(l2);
			
			this.updateWindData();

			this.send( new InfoProtocol( 
					altitude, 
					uuid, 
					latitude, 
					longitude, 
					latChar, 
					lonChar, 
					headingTrue, 
					headingMagnetic, 
					speed,
					speed * 1.852, 
					relativeWindSpeed, 
					trueWindSpeed, 
					apparentWindSpeed, 
					apparentWindAngle,
					this.cms) 
			);
			
			
			try {
				Thread.sleep((long) simulationSpeed);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	private void updateWindData() {
		headingMagnetic = headingTrue + compassDeclination.FindDeclination(latitude, longitude) + compassDeviation.FindDeviation(headingTrue);

		if (latitude > 0) {
			latChar = "N";
		} else {
			latChar = "S";
		}
		
		if (longitude > 0) {
			lonChar = "E";
		} else {
			lonChar = "W";
		}
		
		relativeWindSpeed = trueWindDirection - headingTrue;
		if (relativeWindSpeed < 0) {
			relativeWindSpeed = relativeWindSpeed + 360;
		}
		
		trueWindAngle = relativeWindSpeed;
		if (relativeWindSpeed > 180) {
			trueWindAngle = relativeWindSpeed - 360 ;
		}
		
		apparentWindSpeed = Math.sqrt(trueWindSpeed * trueWindSpeed + speed * speed + 2 * trueWindSpeed * speed * Math.cos(Math.toRadians(trueWindAngle)));
		apparentWindAngle = Math.toDegrees(Math.acos((trueWindSpeed * Math.cos(Math.toRadians(trueWindAngle)) + speed) / apparentWindSpeed));
		if (relativeWindSpeed > 180) {
			apparentWindAngle = 360 - apparentWindAngle;
		}

	}

	public void setElevatorError(double error) {
		this.elevatorError = error;
	}

	public void setElevatorPid(Double p, Double i, Double d) {
		elevator.setPid(p, i, d);
	}
	
}