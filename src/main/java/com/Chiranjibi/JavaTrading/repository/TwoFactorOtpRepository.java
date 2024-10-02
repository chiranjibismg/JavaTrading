package com.Chiranjibi.JavaTrading.repository;

import com.Chiranjibi.JavaTrading.models.TwoFactorOTP;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TwoFactorOtpRepository extends JpaRepository<TwoFactorOTP, String> {

    TwoFactorOTP findByUserId(Long userId);
}
