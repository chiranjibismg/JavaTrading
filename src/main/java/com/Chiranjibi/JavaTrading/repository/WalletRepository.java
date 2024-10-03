package com.Chiranjibi.JavaTrading.repository;

import com.Chiranjibi.JavaTrading.models.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet,Long> {

   Wallet findByUserId(long userId);


}
