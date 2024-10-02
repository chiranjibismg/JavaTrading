package com.Chiranjibi.JavaTrading.repository;

import com.Chiranjibi.JavaTrading.models.VerificationCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationCodeRepository extends JpaRepository<VerificationCode, Long> {

    public VerificationCode findByUserId(Long userId) ;

}
