package br.com.cmabreu.pilot;

public class Elevator extends Thread {
	private MiniPID miniPID; 
	private Airplane airplane;
	private double targetAltitude;
	private double currentAltitude;
	private Double elevatorPosition;
	private double error;
	private final int ELEVATOR_LIMIT = 25; // Limite do leme ( -25 ate 25 )
	private final double ELEVATOR_STEP = 0.1;  // O quando o leme se desloca por vez em graus. Max = 0.1
	
	
	public Elevator( double p, double i, double d,  Airplane airplane ) {
		this.airplane = airplane;
		this.miniPID = new MiniPID(p,i,d); 
		this.miniPID.setOutputLimits( ELEVATOR_LIMIT );
		this.miniPID.setOutputRampRate( ELEVATOR_STEP );
		this.miniPID.setSetpointRange(15000);
		this.targetAltitude = airplane.getAltitude();
		this.currentAltitude =  targetAltitude;
		this.miniPID.setSetpoint( targetAltitude );
	}
	
	public void setPid( double p, double i, double d ) {
		miniPID.setP(p);
		miniPID.setI(i);
		miniPID.setD(d);
	}
	
	public void setAltitudeTo( double targetAltitude ) {
		this.targetAltitude = targetAltitude;
	}
	
	public double getTargetAltitude() {
		return this.targetAltitude;
	}
	
	public double getCurrentAltitude() {
		return this.currentAltitude;
	}
	
	public int getElevatorPosition() {
		return this.elevatorPosition.intValue();
	}
	
	
	@Override
	public void run() {
		
		while (true) {
			elevatorPosition = miniPID.getOutput(currentAltitude, targetAltitude);
			airplane.setElevatorPosition( elevatorPosition );
			currentAltitude =  airplane.getAltitude();
			error = targetAltitude - currentAltitude;
			airplane.setElevatorError( error );
			try {
				Thread.sleep( (long) airplane.getSimulationSpeed() );
			} catch (InterruptedException e) {
				e.printStackTrace();
			}				
			
		}
		
	}
	
}
