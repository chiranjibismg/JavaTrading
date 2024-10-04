package com.Chiranjibi.JavaTrading.service;

import com.Chiranjibi.JavaTrading.domain.WalletTransactionType;
import com.Chiranjibi.JavaTrading.models.Wallet;
import com.Chiranjibi.JavaTrading.models.WalletTransaction;

import java.util.List;

public interface WalletTransactionService {

    WalletTransaction createTransaction(Wallet wallet,
                                        WalletTransactionType type,
                                        String transferId,
                                        String purpose,
                                        Long amount
    );

    List<WalletTransaction> getTransactions(Wallet wallet, WalletTransactionType type);
}
