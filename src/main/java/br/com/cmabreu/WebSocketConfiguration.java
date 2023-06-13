package br.com.cmabreu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;

import br.com.cmabreu.services.CommunicatorService;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfiguration implements WebSocketMessageBrokerConfigurer {

	@Autowired private CommunicatorService comm;
	
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/ws").setAllowedOriginPatterns("*").withSockJS();
		registry.addEndpoint("/ws").setAllowedOrigins("*");		 
	}	

	@EventListener
	public void onDisconnectEvent(SessionDisconnectEvent event) {
		comm.onDisconnect(event);
	}	 
	
	
	@EventListener
	public void onSubscribeEvent(SessionSubscribeEvent event) {
		comm.onSubscribe(event);
	}
				 
	 
}
