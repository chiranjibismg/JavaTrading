package com.Chiranjibi.JavaTrading.repository;

import com.Chiranjibi.JavaTrading.models.ForgotPasswordToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ForgotPasswordRepository extends JpaRepository<ForgotPasswordToken, String> {

    ForgotPasswordToken findByUserId(Long userId);



}
