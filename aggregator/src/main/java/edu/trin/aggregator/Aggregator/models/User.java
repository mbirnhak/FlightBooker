package edu.trin.aggregator.Aggregator.models;

public class User {
    private String username;
    private String password;
    private String email;

    public User() {}

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
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
