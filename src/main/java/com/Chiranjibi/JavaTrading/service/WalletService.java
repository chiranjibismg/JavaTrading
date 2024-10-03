package com.Chiranjibi.JavaTrading.service;

import com.Chiranjibi.JavaTrading.models.Orders;
import com.Chiranjibi.JavaTrading.models.User;
import com.Chiranjibi.JavaTrading.models.Wallet;

public interface WalletService {

    Wallet getUserWallet(User user);

    Wallet addBalance(Wallet wallet,Long money);

    Wallet findWalletById(Long id) throws Exception;

    Wallet walletToWalletTransfer(User sender ,Wallet receiverWallet,Long amount) throws Exception;

    Wallet payOrderPayment(Orders order, User user) throws Exception;
}
