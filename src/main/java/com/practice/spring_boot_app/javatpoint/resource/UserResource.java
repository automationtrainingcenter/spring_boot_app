package com.practice.spring_boot_app.javatpoint.resource;

import com.practice.spring_boot_app.javatpoint.bean.User;
import com.practice.spring_boot_app.javatpoint.service.UserDAOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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

}
