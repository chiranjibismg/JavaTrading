package com.Chiranjibi.JavaTrading.service;

import com.Chiranjibi.JavaTrading.models.PaymentDetails;
import com.Chiranjibi.JavaTrading.models.User;

public interface PaymentDetailsService {
    public PaymentDetails addPaymentDetails(String accountNumber,
                                            String accountHolderName,
                                            String ifsc,
                                            String bankName,
                                            User user
    );

    public PaymentDetails getUsersPaymentDetails(User user);


}
