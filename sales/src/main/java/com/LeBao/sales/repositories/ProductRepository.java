package com.LeBao.sales.repositories;

import com.LeBao.sales.models.Category;
import com.LeBao.sales.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE p.category.id = :categoryId")
    List<Product> findAllByCategoryId(Long categoryId);

    Page<Product> findByProductNameContainingIgnoreCase(String keyword, Pageable pageable);

    Page<Product> findAll(Pageable pageable);
}
