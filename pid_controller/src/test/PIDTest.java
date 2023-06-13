package test;

import pid.MiniPID;

public class PIDTest {

	public static void main(String[] args) {
		
		
		
		MiniPID miniPID = new MiniPID(0.25, 0.01, 0.4);
		
		miniPID.setOutputLimits(5);
		//miniPID.setMaxIOutput(2);
		//miniPID.setOutputRampRate(3);
		//miniPID.setOutputFilter(.3);
		miniPID.setSetpointRange(40);

		double target=100;
		double actual=0;
		double output=0;
		
		miniPID.setSetpoint(0);
		miniPID.setSetpoint(target);
		
		System.err.printf("Target\tActual\tOutput\tError\n");
		
		for (int i = 0; i < 100; i++){
			output = miniPID.getOutput(actual, target);
			actual = actual + output;
			System.out.printf("%3.2f\t%3.2f\t%3.2f\t%3.2f\n", target, actual, output, (target-actual));
		}		

	}

}
