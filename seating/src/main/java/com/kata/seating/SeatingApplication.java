package com.kata.seating;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.kata.seating.dao")
public class SeatingApplication {

	public static void main(String[] args) {
		SpringApplication.run(SeatingApplication.class, args);
	}

}
