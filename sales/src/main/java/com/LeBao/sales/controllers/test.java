package com.LeBao.sales.controllers;

import com.LeBao.sales.models.Product;
import com.LeBao.sales.models.Review;
import com.LeBao.sales.models.User;
import com.LeBao.sales.repositories.ProductRepository;
import com.LeBao.sales.repositories.ReviewRepository;
import com.LeBao.sales.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@RestController
public class test {

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

    @PostMapping("/udm/{id}")
    public ResponseEntity<String> udm(@PathVariable Long id) {
//        Product product = productRepository.findById(id).get();
//        List<String> fileNames = new ArrayList<>();
//        for (String filePath: product.getImages()) {
//            Path path = FileSystems.getDefault().getPath(filePath);
//            fileNames.add(path.getFileName().toString());
//        }
//        product.setImages(fileNames);
//        productRepository.save(product);
//        return ResponseEntity.ok("ok");


        Review review = reviewRepository.findById(id).get();
        List<String> fileNames = new ArrayList<>();
        for (String filePath: review.getImageReviews()) {
            Path path = FileSystems.getDefault().getPath(filePath);
            fileNames.add(path.getFileName().toString());
        }
        review.setImageReviews(fileNames);
        reviewRepository.save(review);
        return ResponseEntity.ok("ok");
    }
}