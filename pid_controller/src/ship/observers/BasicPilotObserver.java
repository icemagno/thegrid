package ship.observers;

public class BasicPilotObserver implements PilotObserver {

	@Override
	public void log(int rudderPosition, double currentAzimuth, double targetAzimuth, double error) {
		System.out.println("Leme: " + rudderPosition + "   Proa: " + currentAzimuth + "   Destino: " + targetAzimuth + "  Erro: " + error );
	}

}
