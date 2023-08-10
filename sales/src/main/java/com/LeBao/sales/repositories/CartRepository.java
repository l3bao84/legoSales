package com.LeBao.sales.repositories;

import com.LeBao.sales.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
