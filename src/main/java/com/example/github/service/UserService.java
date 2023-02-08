package com.example.github.service;

import com.example.github.model.User;
import com.example.github.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        Optional<User> userOptional = userRepository.findByName(user.getName());
        if (userOptional.isPresent()) throw new IllegalStateException("Name taken");
        userOptional = userRepository.findUserByEmail(user.getEmail());
        if (userOptional.isPresent()) throw new IllegalStateException("User with the same email already exists");
        return userRepository.save(user);
    }
}
