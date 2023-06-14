package br.com.cmabreu.pilot.observers;

import br.com.cmabreu.pilot.InfoProtocol;

public class InfoObserver {

	public void send(InfoProtocol info) {
		System.out.println( " >> True Wind: " + info.getTrueWindSpeedS() );
	}

}
