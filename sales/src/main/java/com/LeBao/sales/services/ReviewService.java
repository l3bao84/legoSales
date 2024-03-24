package com.LeBao.sales.services;

import com.LeBao.sales.entities.Product;
import com.LeBao.sales.entities.Review;
import com.LeBao.sales.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public Page<Review> getReviewsByProductId(long id, int page) {
        int pageSize = 4;
        Pageable pageable; Page<Review> reviews = null;
        pageable = PageRequest.of(page, pageSize);
        reviews = reviewRepository.getReviewsByProductId(id, pageable);

        return reviews;
    }

}
