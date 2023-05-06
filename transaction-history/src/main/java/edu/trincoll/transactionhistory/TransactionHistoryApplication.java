package edu.trincoll.transactionhistory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import edu.trincoll.transactionhistory.models.Transaction;
import edu.trincoll.transactionhistory.repositories.TransactionRepository;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "edu.trincoll.transactionhistory.repositories")
public class TransactionHistoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransactionHistoryApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(TransactionRepository repo){
		return args -> {
			Transaction Transaction1 = new Transaction("bennysimoes", 1, 1, 50.00);
			Transaction Transaction2 = new Transaction("mattbirnhak", 2, 1, 50.00);

			if(repo.count() == 0){
				repo.insert(Transaction1);
				repo.insert(Transaction2);
			}
		};
	}
}
