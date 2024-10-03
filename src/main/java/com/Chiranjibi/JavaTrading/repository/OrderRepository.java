package com.Chiranjibi.JavaTrading.repository;

import com.Chiranjibi.JavaTrading.models.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Orders, Long> {


    public List<Orders> findByUserId(Long userId);
}
