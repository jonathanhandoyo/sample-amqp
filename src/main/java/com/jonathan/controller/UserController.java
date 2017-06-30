package com.jonathan.controller;

import com.jonathan.domain.User;
import com.jonathan.exception.NotFoundException;
import com.jonathan.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RequestMapping("/users")
@RestController
@Slf4j
public class UserController {

    private final UserRepository userRepository;

    @GetMapping
    public List<User> getUsers(@RequestParam String name) {
        return this.userRepository.findAllByName(name).collect(Collectors.toList());
    }

    @PostMapping
    public User postUser(@RequestBody User user) {
        return this.userRepository.save(user);
    }

    @GetMapping("/{id}")
    public User putUser(@PathVariable String id) {
        return this.userRepository.findOne(id);
    }

    @PutMapping("/{id}/providers")
    public User putUser(@PathVariable String id, @RequestBody User.Provider provider) {
        User user = this.userRepository.findOne(id);
        if (user == null) throw new NotFoundException();

        user.getProviders().add(provider);
        return this.userRepository.save(user);
    }
}
