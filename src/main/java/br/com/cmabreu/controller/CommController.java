package br.com.cmabreu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;

import br.com.cmabreu.services.CommunicatorService;

@Controller
public class CommController {

	@Autowired private CommunicatorService comm;
	
	@MessageMapping("/main_channel")
	public void broadcastChannel(@Payload String message, MessageHeaders messageHeaders) {
		comm.incommingFromWs( message, messageHeaders );
	}
	
	
}
