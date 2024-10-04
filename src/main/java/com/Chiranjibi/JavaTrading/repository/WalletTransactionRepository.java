package com.Chiranjibi.JavaTrading.repository;


import com.Chiranjibi.JavaTrading.models.Wallet;
import com.Chiranjibi.JavaTrading.models.WalletTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WalletTransactionRepository extends JpaRepository<WalletTransaction,Long> {

    List<WalletTransaction> findByWalletOrderByDateDesc(Wallet wallet);

}
