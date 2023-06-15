package br.com.cmabreu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.cmabreu.services.VesselService;

@RestController
@RequestMapping("/pilot")
public class PilotController {

	@Autowired private VesselService vesselService;
	
    @GetMapping(value = "/setspeed", produces=MediaType.APPLICATION_JSON_VALUE )
    public void setSpeed( @RequestParam(value="speed",required=true) Long speed, @RequestParam(value="uuid",required=true) String uuid ) {
    	vesselService.setSpeed(speed, uuid);
    }		

    
    @GetMapping(value = "/setheading", produces=MediaType.APPLICATION_JSON_VALUE )
    public void setHeading( @RequestParam(value="heading",required=true) Long heading, @RequestParam(value="uuid",required=true) String uuid ) {
    	vesselService.setHeading(heading, uuid);
    }	

    
    @GetMapping(value = "/settimespeed", produces=MediaType.APPLICATION_JSON_VALUE )
    public void setTimeSpeed( @RequestParam(value="t",required=true) Integer t, @RequestParam(value="uuid",required=true) String uuid ) {
    	vesselService.setTimeSpeed( t, uuid );
    }    
    
    @GetMapping(value = "/setpid", produces=MediaType.APPLICATION_JSON_VALUE )
    public void setPid( @RequestParam(value="p",required=true) Double p,
    		@RequestParam(value="i",required=true) Double i,
    		@RequestParam(value="d",required=true) Double d,
    		@RequestParam(value="uuid",required=true) String uuid ) {
    	vesselService.setPid(p, i, d, uuid);
    }	    
}
