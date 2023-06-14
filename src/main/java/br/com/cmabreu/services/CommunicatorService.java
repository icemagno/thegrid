package br.com.cmabreu.services;

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

@Service
public class CommunicatorService {

	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Autowired
	private SimpMessagingTemplate messagingTemplate;	
	
	@EventListener
	public void onDisconnectEvent(SessionDisconnectEvent event) {
		onDisconnect(event);
	}	 
	
	@EventListener
	public void onSubscribeEvent(SessionSubscribeEvent event) {
		onSubscribe(event);
	}	
	
	public void broadcastData( String channel, String data ) {
		
		// To Rabbit-MQ
		rabbitTemplate.convertAndSend( channel, data );
		
    	// To websocket
    	messagingTemplate.convertAndSend("/" + channel, data );
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
   			System.out.println( inputProtocol.toString(5) );
    	} catch ( Exception e ) {
    		e.printStackTrace();
    	}
    }	

    @RabbitListener( queues = {"ping"} )
    public void receivePing(@Payload String payload) {
    	try {
    		JSONObject inputProtocol = new JSONObject( payload );    
   			System.out.println( inputProtocol.toString(5) );
    	} catch ( Exception e ) {
    		e.printStackTrace();
    	}
    }	    
    
}
