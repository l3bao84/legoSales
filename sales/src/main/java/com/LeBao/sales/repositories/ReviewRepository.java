package com.LeBao.sales.repositories;

import com.LeBao.sales.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
