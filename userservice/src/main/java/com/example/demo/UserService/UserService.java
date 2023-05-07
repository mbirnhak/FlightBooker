package com.example.demo.UserService;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entities.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {
   @Autowired
   private UserRepository repository;
   
   public UserService() {}
   
   public List<User> getAllUsers(){
    return repository.findAll();
   }

   public User getUserByUsername(String username1){
    List<User> users = repository.findAll();
        
        for(int i = 0; i < users.size(); i++){
            if((users.get(i).getUsername()).equals(username1)){
                return users.get(i);
            }
        }

        return null;
   }

   public String createUser(User user){
    repository.save(user);
    return "WELCOME TO OUR WEBSITE " + user.getUsername();
   }

   public String updateUserPassword(User user, String password){
    User user_in_database;

    if(this.getUserByUsername(user.getUsername()) != null && this.getUserByUsername(user.getUsername()).getPassword().equals(password)){
        user_in_database = this.getUserByUsername(user.getUsername());
        user_in_database.setPassword(user.getPassword());
        repository.save(user_in_database);
        
        if(user_in_database.getPassword().equals(user.getPassword())){
        return "YOU HAVE SUCCESSFULY CHANGED YOUR PASSWORD";
        }
    }
    return "OPERATION FAILED";
   }

   public String deleteUser(String username, String password){
    User user = this.getUserByUsername(username);
    
    if(user != null && user.getPassword().equals(password)){
        repository.delete(user);
        System.out.println("after delete statement");

        if(this.getUserByUsername(username) == null){
            System.out.println("after if statement");
            return "YOU HAVE SUCCESSFULY DELETED YOUR ACCOUNT. WE ARE SAD YOU ARE NO LONGER WITH US!";
        }
    }
    return "OPERATION FAILED";
   }  
}
