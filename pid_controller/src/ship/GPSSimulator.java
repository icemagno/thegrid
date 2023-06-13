/**
 * 
 */
package ship;

import ship.observers.InfoObserver;

/**
 * @author tony
 *
 */
public class GPSSimulator extends Thread {

	private InfoObserver observer;
	
	private CompassDeviation compassDeviation;
	private CompassDeclination compassDeclination;
	private double trueWindSpeed;
	private double trueWindDirection;
	private double trueWindAngle ;
	private double apparentWindSpeed;
	private double apparentWindAngle;
	private double boatSpeed;
	private double headingTrue;
	private double headingMagnetic;
	private double latitude;
	private double longitude;

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

	public void setBoatSpeed(double b) {
		boatSpeed = b;
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

	public GPSSimulator( InfoObserver observer ) {
		this.observer = observer;
		compassDeviation = new CompassDeviation();
		compassDeclination = new CompassDeclination();
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
			
			String headingTrueS = String.format("%3.1f", headingTrue); 
			String headingMagneticS = String.format("%3.1f", headingMagnetic);
			String boatSpeedS = String.format("%3.1f", boatSpeed);
			String boatSpeedKN = String.format("%3.1f",  boatSpeed * 1.852);

			double r = trueWindDirection - headingTrue;
			if (r < 0) {
				r = r + 360;
			}
			
			String relativeWindSpeedS = String.format("%3.0f", r);
			String trueWindSpeedS = String.format("%2.1f", trueWindSpeed);

			trueWindAngle = r;
			if (r > 180) {
				trueWindAngle = r - 360 ;
			}
			
			apparentWindSpeed = Math.sqrt(trueWindSpeed * trueWindSpeed + boatSpeed * boatSpeed + 2 * trueWindSpeed * boatSpeed * Math.cos(Math.toRadians(trueWindAngle)));
			apparentWindAngle = Math.toDegrees(Math.acos((trueWindSpeed * Math.cos(Math.toRadians(trueWindAngle)) + boatSpeed) / apparentWindSpeed));
			if (r > 180) {
				apparentWindAngle = 360 - apparentWindAngle;
			}

			String apparentWindAngleS = String.format("%3.0f", apparentWindAngle); 
			String apparentWindSpeedS = String.format("%2.1f", apparentWindSpeed);			

			/*
			double compassDec = compassDeclination.FindDeclination(latitude, longitude);
			String compassDecChar = "E";
			if ( compassDec > 0) {
				compassDecChar = "E";
			} else {
				compassDecChar = "W";
			}
			*/
			
			InfoProtocol info = new InfoProtocol(latitude, longitude, latChar, lonChar, headingTrueS, headingMagneticS, boatSpeedS,
					boatSpeedKN,relativeWindSpeedS,trueWindSpeedS, apparentWindSpeedS, apparentWindAngleS);

			observer.send( info );
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
			
		}
	}
}
