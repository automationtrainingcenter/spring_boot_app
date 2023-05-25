package com.practice.spring_boot_app.javatpoint.resource;

import com.practice.spring_boot_app.javatpoint.bean.Post;
import com.practice.spring_boot_app.javatpoint.bean.User;
import com.practice.spring_boot_app.javatpoint.excepitons.UserNotFoundException;
import com.practice.spring_boot_app.javatpoint.repositories.PostRepository;
import com.practice.spring_boot_app.javatpoint.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class UserJPAResource {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/jpa/users")
    public List<User> getUsers() {
        return this.userRepository.findAll();
    }

    @GetMapping("/jpa/users/{id}")
    public User getUserByID(@PathVariable int id) {
        Optional<User> user = this.userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new UserNotFoundException("User is not with id "+id);
        }

    }

    @PostMapping("/jpa/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
        User savedUser = this.userRepository.save(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/jpa/users/{id}")
    public void deleteUser(@PathVariable int id) {
        this.userRepository.deleteById(id);
    }

    @GetMapping("/jpa/users/{id}/posts")
    public List<Post> getPostsByUserId(@PathVariable int id) {
        Optional<User> user = this.userRepository.findById(id);
        if(!user.isPresent())
            throw new UserNotFoundException("User not exists with id "+id);
        return user.get().getPosts();
    }

    @PostMapping("/jpa/users/{id}/posts")
    public ResponseEntity<Object> createPost(@PathVariable int id, @RequestBody Post post) {
        Optional<User> user = this.userRepository.findById(id);
        if (!user.isPresent()) {
            throw new UserNotFoundException("Uer not exists with id "+id);
        }
        post.setUser(user.get());
        this.postRepository.save(post);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(post.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

}
