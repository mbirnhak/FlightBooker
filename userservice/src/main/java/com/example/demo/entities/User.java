package com.example.demo.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "users")
public class User{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    private String username;
    private String password;
    private String email;

    public User() {}

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public Integer getId(){
        return Id;
    }
    public void setId(Integer value){
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
