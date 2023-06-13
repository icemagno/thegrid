package br.com.cmabreu.services;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import br.com.cmabreu.misc.Provider;
import br.com.cmabreu.model.CustomOAuth2User;
import br.com.cmabreu.model.CustomUserDetails;
import br.com.cmabreu.model.User;
import br.com.cmabreu.repository.UserRepository;

@Service
@Order( Ordered.HIGHEST_PRECEDENCE )
public class UserService extends DefaultOAuth2UserService implements UserDetailsService {
	private Logger logger = LoggerFactory.getLogger( UserService.class );
	
    @Autowired
    private UserRepository userRepository;
    
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User user =  super.loadUser(userRequest);
        return new CustomOAuth2User(user);
    }	

    private User getSystem() {
    	return userRepository.getUserByUsername("admin@cmabreu.com.br");
    }
    
    @PostConstruct
    public void init() {
    	try {
    		User system = getSystem();
    		if( system == null ) {
    			addUser( "antares2" , "ADMIN", "admin@cmabreu.com.br", Provider.LOCAL);
    			logger.info("System credentials created and loaded.");
    		} else logger.info("System credentials loaded.");
    	} catch ( Exception e ) {
    		e.printStackTrace();
    	}
    } 
    
    public User addUser( String password, String role, String email, Provider provider) throws Exception {
        User newUser = new User();
        newUser.setUserName( email );
        newUser.setProvider( provider );
        newUser.setEmail( email );
        newUser.setRole( role );
        newUser.setPassword( new BCryptPasswordEncoder( 5 ).encode(password) );
        User user = userRepository.save(newUser);
        return user;
    }
     
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	User user = userRepository.getUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Usuário não encontrado");
        }
        processPostLogin( user );
        return new CustomUserDetails(user);
    }	    

    public void processOAuthPostLogin(String username) {
        User existUser = userRepository.getUserByUsername(username);
        processPostLogin( existUser );
    }

    
    private void processPostLogin( User user ) {
    	logger.info("Usuario logou: " + user.getUserName() );
    }
	
}
