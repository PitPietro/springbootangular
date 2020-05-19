package com.example.springboot.controllers;

import com.example.springboot.entities.User;
import com.example.springboot.repositories.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The only implementation detail worth noting here is the use of the @CrossOrigin annotation.
 * As the name implies, the annotation enables Cross-Origin Resource Sharing (CORS) on the server.
 * This step isn't always necessary. Since we are deploying our Angular frontend to http://localhost:4200
 * and our Boot backend to http://localhost:8081, the browser would otherwise deny requests from one to the other.
 * Regarding the controller methods, getUser() fetches all the User entities from the database. Similarly, the
 * addUser() method persists a new entity in the database, which is passed in the request body.
 * To keep things simple, we deliberately left out of the controller implementation triggering Spring Boot
 * validation before persisting an entity. In production, however, we just can't trust user input, so server-side
 * validation should be a mandatory feature.
 */
@RestController
@CrossOrigin(origins = "http://localhost:4201")
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return (List<User>) userRepository.findAll();
    }

    @PostMapping("/users")
    void addUser(@RequestBody User user) {
        userRepository.save(user);
    }
}
