package br.com.cmabreu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.cmabreu.services.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;	
    
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder( 5 );
    }
     
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService( userService );
        authProvider.setPasswordEncoder( passwordEncoder() );
         
        return authProvider;
    }
 
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }
    
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
        .authorizeRequests()
        .antMatchers(
    		"/adminlte/**", 
    		"/common/**", 
    		"/login", 
    		"/logout", 
    		"/loginform", 
    		"/register",
    		"/createuser",
    		"/shownewuserdata",
    		"/oauth/**"
        ).permitAll()
        .anyRequest().authenticated()
        
        .and()
        .formLogin()
        	.loginPage("/loginform")
        	.defaultSuccessUrl("/", true)
        	.failureUrl("/loginform?error=yes")
    		.loginProcessingUrl("/login")
    		.usernameParameter("username")
    		.passwordParameter("password")        	
        	
        .and()
		.logout()
		.logoutSuccessUrl( "/" )
		.invalidateHttpSession(true);
    }	

}
