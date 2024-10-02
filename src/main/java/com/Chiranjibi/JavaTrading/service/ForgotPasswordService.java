package com.Chiranjibi.JavaTrading.service;

import com.Chiranjibi.JavaTrading.domain.VerificationType;
import com.Chiranjibi.JavaTrading.models.ForgotPasswordToken;
import com.Chiranjibi.JavaTrading.models.User;

public interface ForgotPasswordService {


    ForgotPasswordToken createToken(User user, String id , String otp, VerificationType verificationType,String sendTo);


    ForgotPasswordToken findById(String id);

    ForgotPasswordToken findByUser(Long userId) ;

    void deleteToken(ForgotPasswordToken token);

}
