package br.com.cmabreu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.cmabreu.services.AirplaneService;

@RestController
@RequestMapping("/pilot")
public class PilotController {

	@Autowired private AirplaneService airplaneService;

	
    @GetMapping(value = "/setthrottle", produces=MediaType.APPLICATION_JSON_VALUE )
    public void setSpeed( @RequestParam(value="speed",required=true) Long speed, @RequestParam(value="uuid",required=true) String uuid ) {
    	airplaneService.setThrottle(speed, uuid);
    }		

    @GetMapping(value = "/turnleft", produces=MediaType.APPLICATION_JSON_VALUE )
    public void turnLeft( @RequestParam(value="degree",required=true) Long degree, @RequestParam(value="uuid",required=true) String uuid ) {
    	airplaneService.turnLeft(degree, uuid);
    }
    

    @GetMapping(value = "/turnright", produces=MediaType.APPLICATION_JSON_VALUE )
    public void turnRight( @RequestParam(value="degree",required=true) Long degree, @RequestParam(value="uuid",required=true) String uuid ) {
    	airplaneService.turnRight(degree, uuid);
    }	    
    

    @GetMapping(value = "/up", produces=MediaType.APPLICATION_JSON_VALUE )
    public void up( @RequestParam(value="meters",required=true) Long meters, @RequestParam(value="uuid",required=true) String uuid ) {
    	airplaneService.up(meters, uuid);
    }
    

    @GetMapping(value = "/down", produces=MediaType.APPLICATION_JSON_VALUE )
    public void down( @RequestParam(value="meters",required=true) Long meters, @RequestParam(value="uuid",required=true) String uuid ) {
    	airplaneService.down(meters, uuid);
    }	    
    
    
    // ************************************************************************************************************************
    // ************************************************************************************************************************
    // ************************************************************************************************************************
    
    
    @GetMapping(value = "/setheading", produces=MediaType.APPLICATION_JSON_VALUE )
    public void setHeading( @RequestParam(value="heading",required=true) Long heading, @RequestParam(value="uuid",required=true) String uuid ) {
    	airplaneService.setHeading(heading, uuid);
    }	

    @GetMapping(value = "/setaltitude", produces=MediaType.APPLICATION_JSON_VALUE )
    public void setAltitude( @RequestParam(value="altitude",required=true) Long altitude, @RequestParam(value="uuid",required=true) String uuid ) {
    	airplaneService.setAltitude(altitude, uuid);
    }	
    
    @GetMapping(value = "/settimespeed", produces=MediaType.APPLICATION_JSON_VALUE )
    public void setTimeSpeed( @RequestParam(value="t",required=true) Integer t, @RequestParam(value="uuid",required=true) String uuid ) {
    	airplaneService.setSimulationSpeed( t, uuid );
    }    
    
    @GetMapping(value = "/setrudderpid", produces=MediaType.APPLICATION_JSON_VALUE )
    public void setRudderPid( @RequestParam(value="p",required=true) Double p,
    		@RequestParam(value="i",required=true) Double i,
    		@RequestParam(value="d",required=true) Double d,
    		@RequestParam(value="uuid",required=true) String uuid ) {
    	airplaneService.setRudderPid(p, i, d, uuid);
    }
    
    @GetMapping(value = "/setelevatorpid", produces=MediaType.APPLICATION_JSON_VALUE )
    public void setElevatorPid( @RequestParam(value="p",required=true) Double p,
    		@RequestParam(value="i",required=true) Double i,
    		@RequestParam(value="d",required=true) Double d,
    		@RequestParam(value="uuid",required=true) String uuid ) {
    	airplaneService.setElevatorPid(p, i, d, uuid);
    }

    
}
