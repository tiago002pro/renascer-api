package com.api.renascer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RenascerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RenascerApplication.class, args);
	}

}
