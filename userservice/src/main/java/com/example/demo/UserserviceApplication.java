package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.example.demo.entities.User;
import com.example.demo.repository.UserRepository;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.example.demo.repository")
public class UserserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserserviceApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(UserRepository repo){
		return args -> {
			User User1 = new User("bennysimoes", "administrator", "bernardo.simoes@northwoodschool.com");
			User User2 = new User("mattbirnhak", "administrator", "mrbirnhak@gmail.com");

			if(repo.count() == 0){
				repo.insert(User1);
				repo.insert(User2);
			}
		};
	}
}
