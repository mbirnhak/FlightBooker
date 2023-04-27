package edu.trincoll.transactionhistory.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import edu.trincoll.transactionhistory.models.Transaction;
import edu.trincoll.transactionhistory.services.TransactionService;

@RestController
public class TransactionController {
    @Autowired
    private TransactionService transactionservice;

    public TransactionController(TransactionService service) {
        this.transactionservice = service;
    }

    @GetMapping("/get-transactions")
    public List<Transaction> getTransaction(){
        return transactionservice.getAllTransactions();
    }

    @GetMapping("/get-transaction/{username}")
    public ArrayList<String> findTransactionByUsername(@PathVariable String username){
        return transactionservice.findTransactionByUsername(username);
    }

    @PostMapping("/add-transaction")
    public void saveTransaction(@RequestBody Transaction transaction){
        transactionservice.saveTransaction(transaction);
    }
}
