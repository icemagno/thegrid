package br.com.cmabreu.controller;

import java.util.UUID;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.cmabreu.dto.UserDTO;
import br.com.cmabreu.misc.Provider;
import br.com.cmabreu.model.CustomUserDetails;
import br.com.cmabreu.model.User;
import br.com.cmabreu.services.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

    @Value("${spring.application.name}")
    String appName;

    
    @GetMapping("/loginform")
    public String loginForm(Model model, @RequestParam(value="error",required=false) String error) {
        model.addAttribute("appName", appName);
        if( error != null ) model.addAttribute("error", error);
       	return "loginform";
    }
    		
    @GetMapping("/error")
    public String error(Model model) {
        model.addAttribute("appName", appName);
       	return "loginform";
    }

	@GetMapping("/register")
	public String register(Model model) {
	    model.addAttribute("appName", appName);
	    return "register";
	}		

	@PostMapping("/createuser")
	public String createUser(Model model, @RequestParam(value="email",required=true) String email, @RequestParam(value="password",required=true) String password) {
		try {
			User user = userService.addUser(password, "USER", email, Provider.LOCAL );
		} catch ( Exception e ) {
			model.addAttribute("createMessage", "ERRO: Este usuário já existe.");
		    return "register";
		}
		model.addAttribute("appName", appName);		
	    return "shownewuserdata";
	}		

   
    @GetMapping(value = "/whoami", produces=MediaType.APPLICATION_JSON_VALUE )
    public @ResponseBody UserDTO whoami( UsernamePasswordAuthenticationToken principal ) {
    	CustomUserDetails details = (CustomUserDetails)principal.getPrincipal();
    	User user = details.getUser();
        return new UserDTO( user );
    }			

    
   
}
