package com.Chiranjibi.JavaTrading.repository;

import com.Chiranjibi.JavaTrading.models.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {
}
