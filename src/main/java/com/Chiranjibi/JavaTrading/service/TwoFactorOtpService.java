package com.Chiranjibi.JavaTrading.service;

import com.Chiranjibi.JavaTrading.models.TwoFactorOTP;
import com.Chiranjibi.JavaTrading.models.User;

public interface TwoFactorOtpService {


    TwoFactorOTP createTwoFactorOtp(User user,String otp,String jwt);


    TwoFactorOTP findByUser(Long userId) ;

    TwoFactorOTP findById(String id);

    boolean verifyTwoFactorOtp(TwoFactorOTP twoFactorOtp ,String otp);

    void deleteTwoFactorOtp(TwoFactorOTP twoFactorOtp);


    

}
