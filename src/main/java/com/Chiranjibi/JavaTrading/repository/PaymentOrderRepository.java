package com.Chiranjibi.JavaTrading.repository;

import com.Chiranjibi.JavaTrading.models.PaymentOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentOrderRepository extends JpaRepository<PaymentOrder,Long> {
}
