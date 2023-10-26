package com.LeBao.sales.repositories;

import com.LeBao.sales.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {


    @Query("SELECT o FROM Order o WHERE o.orderStatus = :orderStatus AND o.orderDate = :orderDate")
    List<Order> findByOrderDateBeforeAndOrderStatus(String orderStatus, LocalDate orderDate);
}
