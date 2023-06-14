package br.com.cmabreu.services;

import java.util.Calendar;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@EnableScheduling
public class CommPingTestService {
	
	@Autowired private CommunicatorService comm;
	
	@Scheduled(fixedDelay = 5000)
	private void ping() {
		JSONObject ping = new JSONObject();
		ping.put("test", Calendar.getInstance().get( Calendar.SECOND ) );
		comm.broadcastData("ping", ping.toString() );
	}

}
