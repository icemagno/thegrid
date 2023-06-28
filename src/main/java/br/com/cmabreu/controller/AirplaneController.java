package br.com.cmabreu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.cmabreu.misc.CommandSource;
import br.com.cmabreu.services.AirplaneService;

@RestController
@RequestMapping("/airplane")
public class AirplaneController {
	
	@Autowired private AirplaneService airplaneService;
	
    @GetMapping(value = "/spawn", produces=MediaType.APPLICATION_JSON_VALUE )
    public @ResponseBody String setSpeed( @RequestParam(value="lat",required=true) Long lat, @RequestParam(value="lon",required=true) Long lon, @RequestParam(value="alt",required=true) Integer alt ) {
    	return airplaneService.spawn(lon, lat, 10, alt, CommandSource.CMS_REST, null);
    }	

}
