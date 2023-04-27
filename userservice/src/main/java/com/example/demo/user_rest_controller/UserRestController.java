package com.example.demo.user_rest_controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.UserService.UserService;
import com.example.demo.entities.User;

@RestController
public class UserRestController {
    
    @Autowired
    private UserService userservice;

    public UserRestController(UserService userservice) {
        this.userservice = userservice;
    }

    @GetMapping("/users")
    public List<User> getUsers(){
        return userservice.getAllUsers();
    }

    @GetMapping("users/{username1}")
    public User findUser(@PathVariable String username1){
        return userservice.getUserByUsername(username1);
    }

    @PostMapping(value = "/add-user", consumes = {"application/json"})
    public String createUser(@RequestBody User user){
        return userservice.createUser(user);
    }

    @PutMapping("/update-user/{password}")
    public String updateUserPassword(@RequestBody User user, @PathVariable String password){
        return userservice.updateUserPassword(user, password);
    }

    @DeleteMapping("/delete-user/{username}/{password}")
    public String deleteUser(@PathVariable String username, @PathVariable String password){
        return userservice.deleteUser(username, password);
    }
}
