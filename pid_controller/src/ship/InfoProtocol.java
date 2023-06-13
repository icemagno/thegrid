package ship;

public class InfoProtocol {
	private double latitude;
	private double longitude;
	private String latChar;
	private String lonChar;
	private String headingTrueS;
	private String headingMagneticS;
	private String boatSpeedS;
	private String boatSpeedKN;
	private String relativeWindSpeedS;
	private String trueWindSpeedS;
	private String apparentWindSpeed;
	private String apparentWindAngle;

	public InfoProtocol(double latitude, double longitude, String latChar, String lonChar, String headingTrueS, 
			String headingMagneticS, String boatSpeedS, String boatSpeedKN, String relativeWindSpeedS, String trueWindSpeedS, 
			String apparentWindSpeed, String apparentWindAngle ) {
		this.latChar = latChar;
		this.latitude = latitude;
		this.lonChar = lonChar;
		this.longitude = longitude;
		this.headingTrueS = headingTrueS;
		this.headingMagneticS = headingMagneticS;
		this.boatSpeedS = boatSpeedS;
		this.boatSpeedKN = boatSpeedKN;
		this.relativeWindSpeedS = relativeWindSpeedS;
		this.trueWindSpeedS = trueWindSpeedS;
		this.apparentWindSpeed = apparentWindSpeed;
		this.apparentWindAngle = apparentWindAngle;
	}

	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public String getLatChar() {
		return latChar;
	}

	public String getLonChar() {
		return lonChar;
	}

	public String getHeadingTrueS() {
		return headingTrueS;
	}

	public String getHeadingMagneticS() {
		return headingMagneticS;
	}

	public String getBoatSpeedS() {
		return boatSpeedS;
	}

	public String getBoatSpeedKN() {
		return boatSpeedKN;
	}

	public String getRelativeWindSpeedS() {
		return relativeWindSpeedS;
	}

	public String getTrueWindSpeedS() {
		return trueWindSpeedS;
	}

	public String getApparentWindSpeed() {
		return apparentWindSpeed;
	}

	public String getApparentWindAngle() {
		return apparentWindAngle;
	}
	
	
	
}
