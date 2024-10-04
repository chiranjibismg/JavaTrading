package com.Chiranjibi.JavaTrading.service;


import com.Chiranjibi.JavaTrading.domain.PaymentMethod;
import com.Chiranjibi.JavaTrading.models.PaymentOrder;
import com.Chiranjibi.JavaTrading.models.User;
import com.Chiranjibi.JavaTrading.response.PaymentResponse;
import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;

public interface PaymentService {

    PaymentOrder createOrder(User user, Long amount, PaymentMethod paymentMethod);

    PaymentOrder getPaymentOrderById(Long id) throws Exception;

    Boolean proceedPaymentOrder(PaymentOrder paymentOrder,
                                String paymentId) throws RazorpayException;

    PaymentResponse createRazorpayPaymentLink(User user,
                                              Long Amount,
                                              Long orderId) throws RazorpayException;

    PaymentResponse createStripePaymentLink(User user, Long Amount,
                                            Long orderId) throws StripeException;
}
