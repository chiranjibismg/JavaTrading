package com.Chiranjibi.JavaTrading.repository;

import com.Chiranjibi.JavaTrading.models.Withdrawal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WithdrawalRepository extends JpaRepository<Withdrawal, Long> {

    List<Withdrawal> findByUserId(Long userId);
}
