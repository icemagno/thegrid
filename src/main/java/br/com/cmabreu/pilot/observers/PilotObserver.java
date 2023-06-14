package br.com.cmabreu.pilot.observers;

public class PilotObserver {

	public void log(int rudderPosition, double currentAzimuth, double targetAzimuth, double error) {
		System.out.println("Leme: " + rudderPosition + "   Proa: " + currentAzimuth + "   Destino: " + targetAzimuth + "  Erro: " + error );
	}

}
