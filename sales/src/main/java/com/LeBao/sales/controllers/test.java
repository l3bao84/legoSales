package com.LeBao.sales.controllers;

import com.LeBao.sales.entities.Product;
import com.LeBao.sales.entities.User;
import com.LeBao.sales.repositories.ProductRepository;
import com.LeBao.sales.repositories.ReviewRepository;
import com.LeBao.sales.repositories.UserRepository;
import com.LeBao.sales.services.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class test {


    @Autowired
    private StorageService storageService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ReviewRepository reviewRepository;


    @GetMapping("/getUser/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        return ResponseEntity.ok().body(userRepository.findById(id).get());
    }

    @GetMapping("/getProduct/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id) {
        return ResponseEntity.ok().body(productRepository.findById(id).get());
    }

}