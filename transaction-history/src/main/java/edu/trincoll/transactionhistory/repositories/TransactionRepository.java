package edu.trincoll.transactionhistory.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import edu.trincoll.transactionhistory.models.Transaction;

public interface TransactionRepository extends MongoRepository<Transaction, String> {}
