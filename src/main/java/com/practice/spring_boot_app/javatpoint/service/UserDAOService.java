package com.practice.spring_boot_app.javatpoint.service;

import com.practice.spring_boot_app.javatpoint.bean.User;
import com.practice.spring_boot_app.javatpoint.excepitons.UserNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class UserDAOService {

    public static int usersCount = 5;
    //creating an instance of ArrayList
    private static List<User> users = new ArrayList<>();

    //static block
    static {
//adding users to the list
        users.add(new User(1, "John", new Date()));
        users.add(new User(2, "Robert", new Date()));
        users.add(new User(3, "Adam", new Date()));
        users.add(new User(4, "Andrew", new Date()));
        users.add(new User(5, "Jack", new Date()));
    }

    public List<User> findAll() {
        return this.users;
    }

    public User save(User user) {
        if (user.getId() == null) {
            user.setId(++usersCount);
        }
        this.users.add(user);
        return user;
    }


    public User findOne(int id) {
        return this.users.stream().filter(user -> user.getId() == id).findFirst().orElseThrow(() -> new UserNotFoundException("User not found with id : " + id));
    }

    public User deleteOne(int id) {
        for(User user : this.users) {
            if(user.getId() == id) {
                this.users.remove(user);
                return user;
            }
        }
        return null;
    }


}
