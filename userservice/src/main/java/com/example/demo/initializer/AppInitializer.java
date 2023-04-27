package com.example.demo.initializer;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.demo.entities.User;
import com.example.demo.repository.UserRepository;

@Component
public class AppInitializer implements CommandLineRunner{
    private UserRepository repository;
    
    @Autowired
    public AppInitializer(UserRepository repository) {
        this.repository = repository;
    }
    
    @Override
    public void run(String... args) throws Exception{
        repository.saveAll(
        List.of(new User("bennysimoes", "administrator", "bernardo.simoes@northwoodschool.com"),
                new User("mattbirnhak", "administrator", "mrbirnhak@gmail.com"))).forEach(System.out::println);
    }
}
