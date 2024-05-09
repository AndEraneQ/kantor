package com.kantor.service;

import com.kantor.model.User;
import com.kantor.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<?> register(User user) {
        if (userRepository.existsByLogin(user.getLogin())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Login already exists.");
        }
        try {
            userRepository.save(user);
            return ResponseEntity.ok("User registered successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed with register user.");
        }
    }
}


