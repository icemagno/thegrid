/**
 * 
 */
package br.com.cmabreu.pilot;

/**
 * @author tony
 *
 */
public class GPSSimulator extends Thread {

	private IVesselObserver observer;
	
	private CompassDeviation compassDeviation;
	private CompassDeclination compassDeclination;
	private double trueWindSpeed;
	private double trueWindDirection;
	private double trueWindAngle ;
	private double apparentWindSpeed;
	private double apparentWindAngle;
	private double speed;
	private double headingTrue;
	private double headingMagnetic;
	private double latitude;
	private double longitude;
	private String uuid;
	private int updateSpeed = 0;

	/**
	 * Convert lat/long from decimal degrees to the format expected in NMWEA
	 * 0183 sentences. We calculate to four decimal places but three would
	 * probably be more than enough.
	 */
	public static String decimalToGPS(double L) {
		return String.format("%.4f", ((int) Math.abs(L) * 100) + ((Math.abs(L) - (int) Math.abs(L)) * 60));
	}

	public void setWind(double s, double d) {
		trueWindSpeed = s;
		trueWindDirection = d;
	}

	public double getTrueWindSpeed() {
		return trueWindSpeed;
	}

	public double getTrueWindAngle() {
		if (trueWindAngle < 180) {
			return trueWindAngle;
		} else {
			return trueWindAngle-360 ;
		}
	}

	public double getApparentWindSpeed() {
		return apparentWindSpeed;
	}

	public double getApparentWindAngle() {
		if (apparentWindAngle < 180) {
		return apparentWindAngle;
		} else {
			return apparentWindAngle-360 ;
		}
	}

	public void setSpeed(double b) {
		speed = b;
	}

	public void setHeading(double h) {
		headingTrue = h;
	}

	public void setLatitude(double lat) {
		latitude = lat;
	}

	public void setLongitude(double lon) {
		longitude = lon;
	}

	public double getHeadingMagnetic() {
		return headingMagnetic;
	}

	public GPSSimulator( String uuid, IVesselObserver observer, int updateSpeed) {
		this.observer = observer;
		this.updateSpeed = updateSpeed;
		this.uuid = uuid;
		compassDeviation = new CompassDeviation();
		compassDeclination = new CompassDeclination();
	}
	
	public void setUpdateSpeed(int updateSpeed) {
		this.updateSpeed = updateSpeed;
	}

	@Override
	public void run() {
		while (true) {
			headingMagnetic = headingTrue + compassDeclination.FindDeclination(latitude, longitude) + compassDeviation.FindDeviation(headingTrue);

			String latChar = "N";
			String lonChar = "E";
			
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
			
			double r = trueWindDirection - headingTrue;
			if (r < 0) {
				r = r + 360;
			}
			
			trueWindAngle = r;
			if (r > 180) {
				trueWindAngle = r - 360 ;
			}
			
			apparentWindSpeed = Math.sqrt(trueWindSpeed * trueWindSpeed + speed * speed + 2 * trueWindSpeed * speed * Math.cos(Math.toRadians(trueWindAngle)));
			apparentWindAngle = Math.toDegrees(Math.acos((trueWindSpeed * Math.cos(Math.toRadians(trueWindAngle)) + speed) / apparentWindSpeed));
			if (r > 180) {
				apparentWindAngle = 360 - apparentWindAngle;
			}

			
			observer.send( new InfoProtocol( 
				uuid, 
				latitude, 
				longitude, 
				latChar, 
				lonChar, 
				headingTrue, 
				headingMagnetic, 
				speed,
				speed * 1.852, 
				r, 
				trueWindSpeed, 
				apparentWindSpeed, 
				apparentWindAngle) 
			);
			
			try {
				Thread.sleep( this.updateSpeed );
			} catch (InterruptedException e) {
			}
			
		}
	}
}
