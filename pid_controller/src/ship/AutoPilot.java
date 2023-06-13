package ship;

import pid.MiniPID;
import ship.observers.PilotObserver;

public class AutoPilot extends Thread {
	private MiniPID miniPID; 
	private Vessel ship;
	private double targetAzimuth;
	private double currentAzimuth;
	private Double rudderPosition;
	private PilotObserver observer;
	private double error;
	
	private final int RUDDER_LIMIT = 5; // Limite do leme ( -5 ate 5 )
	private final int RUDDER_STEP = 1;  // O quando o leme se desloca por vez em graus. Max = 5
	
	
	public AutoPilot( Vessel ship, double targetAzimuth ) {
		this.ship = ship;
		this.miniPID = new MiniPID(0.5, 0.1, 0.5); 
		this.miniPID.setOutputLimits( RUDDER_LIMIT );
		this.miniPID.setOutputRampRate( RUDDER_STEP );
		this.miniPID.setSetpointRange(360);		
		this.miniPID.setSetpoint( targetAzimuth );
		this.targetAzimuth = targetAzimuth;
		this.currentAzimuth =  ship.GetHeading();
	}
	

	public void setCourseTo( double azimuth ) {
		this.targetAzimuth = azimuth;
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
	
	public double getError() {
		return this.error;
	}
	
	public void setObserver( PilotObserver observer ) {
		this.observer = observer;
	}
	
	@Override
	public void run() {
		
		while (true) {
			
			rudderPosition = miniPID.getOutput(currentAzimuth, targetAzimuth);
			ship.SetRudderPosition( rudderPosition.intValue()  );
			currentAzimuth =  ship.GetHeading();
			error = targetAzimuth - currentAzimuth;
			
			if( observer != null ) {
				observer.log(rudderPosition.intValue(), currentAzimuth, targetAzimuth, error);
			}
			
			try {
				Thread.sleep( (long) ship.getInterval() * 1000 );
			} catch (InterruptedException e) {
				e.printStackTrace();
			}				
			
		}
		
	}
	
}
