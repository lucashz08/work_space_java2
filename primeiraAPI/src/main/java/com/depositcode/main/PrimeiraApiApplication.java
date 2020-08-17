package com.depositcode.main;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PrimeiraApiApplication {

	public static void main(String[] args) {
		// SpringApplication.run(PrimeiraApiApplication.class, args);
		SpringApplication app = new SpringApplication(PrimeiraApiApplication.class);
	       app.setDefaultProperties(Collections.singletonMap("server.port", "8080")); // mudança de porta
	       app.run(args);
	}

}
