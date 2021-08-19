package com.fiap.pet.mspetemaillistener;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MsPetEmailListenerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsPetEmailListenerApplication.class, args);
	}
}
