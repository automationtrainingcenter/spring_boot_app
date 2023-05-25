package com.practice.spring_boot_app.javatpoint.resource;

import com.practice.spring_boot_app.javatpoint.bean.User;
import com.practice.spring_boot_app.javatpoint.excepitons.UserNotFoundException;
import com.practice.spring_boot_app.javatpoint.service.UserDAOService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserResource {

    @Autowired
    private UserDAOService userService;

    @GetMapping("/users")
    public List<User> getUsers() {
        return this.userService.findAll();
    }

    @GetMapping("/users/{id}")
    public User getUserByID(@PathVariable int id) {
        return this.userService.findOne(id);
    }

    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
        User savedUser = this.userService.save(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id) {
        User user = this.userService.deleteOne(id);
        if(user == null) {
            throw new UserNotFoundException("User not found with id "+id);
        }

    }

}
