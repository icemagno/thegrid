package br.com.cmabreu.services;

import java.util.Calendar;

import javax.annotation.PostConstruct;

import org.json.JSONObject;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;

import br.com.cmabreu.misc.CommandSource;
import br.com.cmabreu.misc.ProtocolCommand;

@Service
public class CommunicatorService {

	@Autowired private RabbitTemplate rabbitTemplate;
	@Autowired private SimpMessagingTemplate messagingTemplate;
	@Autowired private AirplaneService airplaneService;
	
	@PostConstruct
	private void init() {
		this.airplaneService.setCommunicator( this );
	}
	
	@EventListener
	public void onDisconnectEvent(SessionDisconnectEvent event) {
		onDisconnect(event);
	}	 
	
	@EventListener
	public void onSubscribeEvent(SessionSubscribeEvent event) {
		onSubscribe(event);
	}	
	
	public void broadcastData( String channel, JSONObject data ) throws Exception {
		data.put("timestamp", Calendar.getInstance().getTimeInMillis() );
		
		// To Rabbit-MQ
		rabbitTemplate.convertAndSend( channel, data.toString() );
		
    	// To websocket
    	messagingTemplate.convertAndSend("/" + channel, data.toString() );
	}

	public void onDisconnect(SessionDisconnectEvent event) {
		String sessionId = new JSONObject( event.getMessage().getHeaders() ).getString("simpSessionId");
		System.out.println( "[" + sessionId +  "]  Recebido pelo CommController: DISCONNECT EVENT" );
	}

	public void onSubscribe(SessionSubscribeEvent event) {
		// TODO Auto-generated method stub
	}

	public void incommingFromWs(String message, MessageHeaders messageHeaders) {
		String sessionId = new JSONObject( messageHeaders ).getString("simpSessionId");
		System.out.println( "[" + sessionId +  "]  Recebido pelo CommController: " );
		System.out.println( message );
	}	
	
    @RabbitListener( queues = {"main_channel"} )
    public void receive(@Payload String payload) {
    	try {
    		JSONObject inputProtocol = new JSONObject( payload );
   			if( !inputProtocol.has("command") ) return;
   			String command = inputProtocol.getString("command");
   			ProtocolCommand pc = ProtocolCommand.valueOf(command);
   			String uuid = inputProtocol.getString("uuid");
   			long data = 0;
   			if( inputProtocol.has("value") ) data = inputProtocol.getLong("value");

    		System.out.println( inputProtocol.toString(5) );

    		
   			switch (pc ) {
				case PC_DOWN:
					airplaneService.down(data, uuid);
					break;
				case PC_LEFT:
					airplaneService.turnLeft(data, uuid);
					break;
				case PC_RIGHT:
					airplaneService.turnRight(data, uuid);
					break;
				case PC_UP:
					airplaneService.up(data, uuid);
					break;
				case PC_THROTTLE:
					airplaneService.setThrottle(data, uuid);
					break;
				case PC_SPAWN:
					spawnAirplane( inputProtocol );
					break;
				default:
					break;
			}
   			
    	} catch ( Exception e ) {
    		e.printStackTrace();
    	}
    }	

    private void spawnAirplane(JSONObject inputProtocol) {
    	try {
    		String uuid = inputProtocol.getString("uuid");
	    	double lat = inputProtocol.getDouble("lat");
	    	double lon = inputProtocol.getDouble("lon");
	    	int alt = inputProtocol.getInt("alt");
	    	long throttle = inputProtocol.getLong("throttle");
			airplaneService.spawn(lon, lat, throttle, alt, CommandSource.CMS_MESSAGEQUEUE, uuid );
    	} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RabbitListener( queues = {"ping"} )
    public void receivePing(@Payload String payload) {
    	try {
   			System.out.println( "PING RECEIVED!" );
    	} catch ( Exception e ) {
    		e.printStackTrace();
    	}
    }	    
    
}
