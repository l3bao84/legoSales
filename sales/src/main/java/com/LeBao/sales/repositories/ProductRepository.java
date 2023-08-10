package com.LeBao.sales.repositories;

import com.LeBao.sales.models.Category;
import com.LeBao.sales.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
