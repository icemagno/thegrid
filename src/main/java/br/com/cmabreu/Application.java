package br.com.cmabreu;

import java.awt.image.BufferedImage;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.BufferedImageHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.amqp.core.Queue;

@SpringBootApplication
@EnableRabbit
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public HttpMessageConverter<BufferedImage> bufferedImageHttpMessageConverter() {
	    return new BufferedImageHttpMessageConverter();
	}	
	
	
    @Bean
    public Queue createMainChannel() {
        return new Queue("main_channel", true);
    }	

    @Bean
    public Queue createPingChannel() {
        return new Queue("ping", true);
    }	    
    
}
