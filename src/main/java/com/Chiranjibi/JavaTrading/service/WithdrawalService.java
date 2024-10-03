package com.Chiranjibi.JavaTrading.service;

import com.Chiranjibi.JavaTrading.models.User;
import com.Chiranjibi.JavaTrading.models.Withdrawal;

import java.util.List;

public interface WithdrawalService {


        Withdrawal requestWithdrawal(Long amount, User user);
        Withdrawal proceedWithdrawal(Long withdrawalId, boolean accept) throws Exception;
        List<Withdrawal> getUsersWithdrawalHistory(User user);
        List<Withdrawal> getAllWithdrawalRequest();
}
