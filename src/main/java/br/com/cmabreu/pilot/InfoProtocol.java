package br.com.cmabreu.pilot;

public class InfoProtocol {
	private String uuid;
	private double latitude;
	private double longitude;
	private String latChar;
	private String lonChar;
	private double headingTrue;
	private double headingMagnetic;
	private double speedKM;
	private double speedKN;
	private double relativeWindSpeed;
	private double trueWindSpeed;
	private double apparentWindSpeed;
	private double apparentWindAngle;
	private double currentAzimuth;
	private double targetAzimuth;
	private double error;	
	private int rudderPosition;
	private int roll;
	private int pitch;
	private double altitude;

	public InfoProtocol(String uuid, double latitude, double longitude, String latChar, String lonChar, double headingTrue, 
			double headingMagnetic, double speedKM, double speedKN, double relativeWindSpeed, double trueWindSpeed, 
			double apparentWindSpeed, double apparentWindAngle ) {
		this.latChar = latChar;
		this.uuid = uuid;
		this.latitude = latitude;
		this.lonChar = lonChar;
		this.longitude = longitude;
		this.headingTrue = headingTrue;
		this.headingMagnetic = headingMagnetic;
		this.speedKM = speedKM;
		this.speedKN = speedKN;
		this.relativeWindSpeed = relativeWindSpeed;
		this.trueWindSpeed = trueWindSpeed;
		this.apparentWindSpeed = apparentWindSpeed;
		this.apparentWindAngle = apparentWindAngle;
		this.roll = this.rudderPosition;
		this.pitch = 0;
		this.altitude = 10000;
	}
	
	public String getUuid() {
		return uuid;
	}

	public double getCurrentAzimuth() {
		return currentAzimuth;
	}
	
	public int getRudderPosition() {
		return rudderPosition;
	}

	public void setRudderPosition(int rudderPosition) {
		this.rudderPosition = rudderPosition;
		this.roll = this.rudderPosition;
	}

	public void setCurrentAzimuth(double currentAzimuth) {
		this.currentAzimuth = currentAzimuth;
	}

	public double getTargetAzimuth() {
		return targetAzimuth;
	}

	public void setTargetAzimuth(double targetAzimuth) {
		this.targetAzimuth = targetAzimuth;
	}

	public double getError() {
		return error;
	}

	public void setError(double error) {
		this.error = error;
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

	public double getHeadingTrue() {
		return headingTrue;
	}

	public double getHeadingMagnetic() {
		return headingMagnetic;
	}

	public double getSpeedKM() {
		return speedKM;
	}

	public double getSpeedKN() {
		return speedKN;
	}

	public double getRelativeWindSpeed() {
		return relativeWindSpeed;
	}

	public double getTrueWindSpeed() {
		return trueWindSpeed;
	}

	public double getApparentWindSpeed() {
		return apparentWindSpeed;
	}

	public double getApparentWindAngle() {
		return apparentWindAngle;
	}

	public int getRoll() {
		return roll;
	}

	public int getPitch() {
		return pitch;
	}
	
	public double getAltitude() {
		return altitude;
	}
	
}
