package com.LeBao.sales.repositories;

import com.LeBao.sales.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("select o from Order o where o.user.userId = :userId and o.orderStatus = :status")
    Set<Order> findByStatusAndUserId(@Param("userId") Long userId, @Param("status") String status);

    @Query("SELECT o FROM Order o WHERE o.orderStatus = :orderStatus AND o.orderDate = :orderDate")
    List<Order> findByOrderDateBeforeAndOrderStatus(String orderStatus, LocalDate orderDate);

    @Query("select o from Order o where o.paymentStatus = :paymentId")
    Optional<Order> findByPaymentStatus(@Param("paymentId") String paymentId);
}
