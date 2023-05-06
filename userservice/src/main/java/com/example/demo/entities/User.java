package com.example.demo.entities;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

@Document
public class User{

    @Id
    private String Id;

    @Indexed(unique = true)
    private String username;
    
    private String password;
    private String email;

    public User() {}

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getId(){
        return Id;
    }
    public void setId(String value){
        Id = value;
    }

    public String getUsername(){
        return username;
    }
    public void setUsername(String value){
        username = value;
    }

    public String getPassword(){
        return password;
    }
    public void setPassword(String value){
        password = value;
    }
    public String getEmail(){
        return email;
    }
    public void setEmail(String value){
        email = value;
    }

   
    @Override
    public String toString() {
        return "USERNAME:" + username + ", PASSWORD:" + password + ", EMAIL:" + email;
    } 
}
