package com.LeBao.sales.controllers;

import com.LeBao.sales.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping("/{productId}")
    public ResponseEntity<?> getReviewsByProductId(@PathVariable long productId,
                                                   @RequestParam(defaultValue = "0", required = false) int page) {
        return ResponseEntity.ok().body(reviewService.getReviewsByProductId(productId, page));
    }
}
