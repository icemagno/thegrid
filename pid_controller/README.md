# shipsimulator
A ship simulator

# Auto pilot using PID controller by 
https://github.com/tekdemo/MiniPID-Java/

# Vessel simulator based on code by 
https://sourceforge.net/projects/nmea-vessel-simulator-in-java/

# Como usar:

```
Vessel vessel = new Vessel( ObserverFactory.getObserver() , interval, Ships.NPA_BRACUI, boatspeed, heading, 
    latitude, longitude, altitude, rudderposition, throttleposition, 2.0);
vessel.start();
```

ObserverFactory.getObserver() é implementação de 

```
public interface InfoObserver {
	public void send( InfoProtocol info );
}
```
onde:

```
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
}
```

O método "send()" do observer será chamado a cada intervalo "interval" de tempo, oferecendo os dados atualizados do navio, constantes na classe "InfoProtocol". Você poderá criar um observer para coletar os dados do navio e atualizar uma interface gráfica ou imprimir os valores na console. Altere o método ```ObserverFactory.getObserver()``` para chamar seu próprio observer.


```Ships.NPA_BRACUI``` é o comprimento do casco do navio, usado para cálculos de deslocamento. Para adicionar outros navios, edite a classe ```Ships```.

```boatspeed, heading, latitude, longitude, altitude, rudderposition, throttleposition, 2.0``` são os valores iniciais do navio. Respectivamente: velocidade, direção (em graus onde o norte é 0/360 e sul é 180), latitude, longitude, altitude (permite simular flutuação variando levemente em relação ao nível do mar), posição do leme (entre -5 e 5, sendo 0 a posição de "meio") e potência do motor (varia entre 0=parado e 100=máxima). O último valor (2.0) é um fator de multiplicação de eficiência do leme, útil para simular a resposta do navio ao comando do leme (navios grandes respondem mais lentamente).


Para controlar o navio, use ```vessel.SetRudderPosition( int p )``` onde ```p``` é o ângulo do leme entre -5 e 5, sendo 0 para o navio ir em linha reta.

Para controlar a velocidade do navio, utilize ```vessel.SetThrottlePosition( int p )``` onde ```p``` é a porcentagem de máquina, sendo 0 para motor desligado e 100 para velocidade máxima.

Consulte a classe ```Vessel``` para outros métodos.




