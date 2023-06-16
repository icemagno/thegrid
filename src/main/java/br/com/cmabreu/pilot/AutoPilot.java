package br.com.cmabreu.pilot;

public class AutoPilot extends Thread {
	private MiniPID miniPID; 
	private Vessel ship;
	private double targetAzimuth;
	private double currentAzimuth;
	private Double rudderPosition;
	private double error;
	private final int RUDDER_LIMIT = 10; // Limite do leme ( -5 ate 5 )
	private final double RUDDER_STEP = 0.1;  // O quando o leme se desloca por vez em graus. Max = 5
	
	
	public AutoPilot( double p, double i, double d,  Vessel ship ) {
		this.ship = ship;
		this.miniPID = new MiniPID(p,i,d); 
		this.miniPID.setOutputLimits( RUDDER_LIMIT );
		this.miniPID.setOutputRampRate( RUDDER_STEP );
		this.miniPID.setSetpointRange(360);
		this.miniPID.setSetpoint( targetAzimuth );
		this.targetAzimuth = ship.getHeading();
		this.currentAzimuth =  targetAzimuth;
	}
	
	public void setPid( double p, double i, double d ) {
		miniPID.setP(p);
		miniPID.setI(i);
		miniPID.setD(d);
	}
	
	public void setCourseTo( double targetAzimuth ) {
		this.targetAzimuth = targetAzimuth;
	}
	
	public double getTargetAzimuth() {
		return this.targetAzimuth;
	}
	
	public double getCurrentAzimuth() {
		return this.currentAzimuth;
	}
	
	public int getRudderPosition() {
		return this.rudderPosition.intValue();
	}
	
	
	@Override
	public void run() {
		
		while (true) {
			rudderPosition = miniPID.getOutput(currentAzimuth, targetAzimuth);
			ship.setRudderPosition( rudderPosition );
			currentAzimuth =  ship.getHeading();
			error = targetAzimuth - currentAzimuth;
			ship.setError( error );
			try {
				Thread.sleep( (long) ship.getInterval() );
			} catch (InterruptedException e) {
				e.printStackTrace();
			}				
			
		}
		
	}
	
}
