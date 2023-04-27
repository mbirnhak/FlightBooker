package edu.trincoll.transactionhistory.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.trincoll.transactionhistory.models.Transaction;
import edu.trincoll.transactionhistory.repositories.TransactionRepository;

@Service
public class TransactionService {
    @Autowired
   private TransactionRepository repository;
   
   public TransactionService() {}
   
   public List<Transaction> getAllTransactions(){
    return repository.findAll();
   }

   public ArrayList<String> findTransactionByUsername(String username){
    
    List<Transaction> trans = repository.findAll();
    ArrayList<Transaction> transactions = new ArrayList<Transaction>();
    ArrayList<String> finalList = new ArrayList<String>();

    for(int i = 0; i < trans.size();i++){
        if(trans.get(i).getUsername().equals(username)){
            transactions.add(trans.get(i));
        }
    }

    if(transactions.size() == 0){
        finalList.add("NO TRANSACTIONS FOUND FOR SPECIFIED USER");
    }else{
        for(int i = 0;i < transactions.size();i++){
            finalList.add(transactions.get(i).toString());
        }  
    }

    return finalList; 
   }

   public void saveTransaction(Transaction transaction){
    repository.save(transaction);
   }
}
