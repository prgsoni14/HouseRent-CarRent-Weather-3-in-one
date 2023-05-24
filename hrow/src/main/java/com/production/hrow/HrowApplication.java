package com.production.hrow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HrowApplication {

	public static void main(String[] args) {
		SpringApplication.run(HrowApplication.class, args);

		System.out.println("The server is up and running at 8080");
	}

}
