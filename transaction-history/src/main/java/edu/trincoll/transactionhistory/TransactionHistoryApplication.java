package edu.trincoll.transactionhistory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TransactionHistoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransactionHistoryApplication.class, args);
	}

	// @Bean
	// CommandLineRunner runner(TransactionRepository repo, MongoTemplate template){
	// 	return args -> {
	// 		Transaction Transaction1 = new Transaction("bennysimoes", 1, 1, 50.00);
	// 		Transaction Transaction2 = new Transaction("mattbirnhak", 2, 1, 50.00);

	// 		if(repo.findAll() == null){
	// 			repo.insert(Transaction1);
	// 			repo.insert(Transaction2);
	// 		}
	// 	};
	// }
}
