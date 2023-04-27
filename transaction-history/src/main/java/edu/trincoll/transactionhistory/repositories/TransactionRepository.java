package edu.trincoll.transactionhistory.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.trincoll.transactionhistory.models.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {}
