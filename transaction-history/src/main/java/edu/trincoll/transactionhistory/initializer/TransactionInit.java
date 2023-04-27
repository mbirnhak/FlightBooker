package edu.trincoll.transactionhistory.initializer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import edu.trincoll.transactionhistory.models.Transaction;
import edu.trincoll.transactionhistory.repositories.TransactionRepository;

@Component
public class TransactionInit implements CommandLineRunner{
    private TransactionRepository repository;
    
    @Autowired
    public TransactionInit(TransactionRepository repository) {
        this.repository = repository;
    }
    
    @Override
    public void run(String... args) throws Exception{
        repository.saveAll(
        List.of(new Transaction("bennysimoes", 1, 1, 50.00),
        new Transaction("mattbirnhak", 2, 1, 50.00))).forEach(System.out::println);
    }
}