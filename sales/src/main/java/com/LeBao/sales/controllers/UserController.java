package com.LeBao.sales.controllers;

import com.LeBao.sales.repositories.ProductRepository;
import com.LeBao.sales.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/getUser/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable Long userId) {
        return ResponseEntity.ok(userRepository.findById(userId).get());
    }

    @GetMapping("/getProduct/{productId}")
    public ResponseEntity<?> getProductById(@PathVariable Long productId) {
        return ResponseEntity.ok(productRepository.findById(productId).get());
    }
}
