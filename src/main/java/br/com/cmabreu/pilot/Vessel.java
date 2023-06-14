/**
 * Main Simulation thread. Runs a loop that recalculates simulation parameters periodically. 
 * The simulation interval can be set when the thread is started.
 */
package br.com.cmabreu.pilot;

import java.util.Random;

import br.com.cmabreu.pilot.observers.InfoObserver;

/**
 * @author Tony Mattheys
 *
 */
public class Vessel extends Thread {
	private double rudderFactor;
	private double rudderPosition;
	private double throttlePosition;
	private double interval;
	private double hullSpeed;
	private double boatSpeed;
	private double heading;
	private double latitude;
	private double longitude;
	private double displacement;
	private double trueWindDirection;
	private double trueWindSpeed;
	private double earthRadius = 3443.89849;
	private GPSSimulator GPS;
	private Random generator = new Random(System.nanoTime());

	public double getInterval() {
		return interval;
	}	
	
	private double calcHullSpeed( int waterLineLengthMeters ) {
		double waterLineLengthFeet = waterLineLengthMeters * 3.2808;
		double hullSpeed = 1.34 * Math.sqrt( waterLineLengthFeet );
		return hullSpeed;
	}
		
	
	/**
	 * Initialize the Simulation
	 */
	public Vessel(InfoObserver observer, double intv, int ship, double spd, double hdg, 
			double lat, double lon, double alt, double rud, double thr, double rudderFactor) {
		
		
		double hullspeed = calcHullSpeed( ship );
		
		this.rudderFactor = rudderFactor;
		this.GPS = new GPSSimulator( observer );
		this.interval = intv;
		this.hullSpeed = hullspeed;
		this.boatSpeed = spd;
		this.heading = hdg;
		this.latitude = lat;
		this.longitude = lon;
		this.rudderPosition = rud;
		this.throttlePosition = thr;
		this.trueWindDirection = generator.nextDouble() * 360;
		this.trueWindSpeed = generator.nextDouble() * 20.0 + 5.0;
		this.GPS.start();
	}

	public void setRudderPosition(int p) {
		rudderPosition = p;
	}
	
	public int getRudderPosition() {
		return ( (Double)rudderPosition ).intValue();
	}	

	public void setThrottlePosition(double t) {
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

	public double getBoatSpeed() {
		return ((Double)boatSpeed).intValue();
	}

	public double getHeadingMagnetic() {
		return GPS.getHeadingMagnetic();
	}

	public double getTrueWindSpeed() {
		return GPS.getTrueWindSpeed();
	}

	public double getTrueWindAngle() {
		return GPS.getTrueWindAngle();
	}

	public double getApparentWindSpeed() {
		return GPS.getApparentWindSpeed();
	}

	public double getApparentWindAngle() {
		return GPS.getApparentWindAngle();
	}

	@Override
	public void run() {
		while (true) {
			/**
			 * Gradually allow the boat speed to converge on the target speed
			 * by adding half of the required delta in each iteration of the 
			 * simulation. Otherwise the speed changes instantly which is
			 * SO unrealistic.
			 */
			if (throttlePosition >= 0) {
				boatSpeed = boatSpeed + (((hullSpeed * throttlePosition / 100) - boatSpeed) / 2);
			}
			GPS.setBoatSpeed(boatSpeed);

			/**
			 * Turn the boat in the direction indicated by the rudder. We multiply the
			 * rudder slider value by a factor to make the turn quicker or slower. At
			 * he end we need to make sure we have not gone through 360 degrees in one
			 * direction or the other and correct as necessary.
			 */
			if (rudderPosition != 0 && boatSpeed != 0) {
				heading = heading + rudderPosition * rudderFactor;
			}
			if (heading < 0) {
				heading = heading + 360;
			}
			if (heading >= 360) {
				heading = heading - 360;
			}
			GPS.setHeading(heading);
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
			GPS.setWind(TWS, TWD);
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
			displacement = boatSpeed * interval / 3600;
			double p2 = Math.asin(Math.sin(Math.toRadians(latitude)) * Math.cos(displacement / earthRadius) + Math.cos(Math.toRadians(latitude)) * Math.sin(displacement / earthRadius) * Math.cos(Math.toRadians(heading)));
			latitude = Math.toDegrees(p2);
			GPS.setLatitude(latitude);
			double l2 = Math.toRadians(longitude) + Math.atan2(Math.sin(Math.toRadians(heading)) * Math.sin(displacement / earthRadius) * Math.cos(Math.toRadians(latitude)), Math.cos(displacement / earthRadius) - Math.sin(Math.toRadians(latitude)) * Math.sin(p2));
			longitude = Math.toDegrees(l2);
			GPS.setLongitude(longitude);
			
			try {
				Thread.sleep((long) interval * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}
}