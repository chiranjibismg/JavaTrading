package com.Chiranjibi.JavaTrading.service;

import com.Chiranjibi.JavaTrading.domain.VerificationType;
import com.Chiranjibi.JavaTrading.models.User;
import com.Chiranjibi.JavaTrading.models.VerificationCode;

public interface VerificationCodeService {


    VerificationCode sendVerificationCode(User user, VerificationType verificationType) ;

    VerificationCode getVerificationCodeById(Long id) throws Exception;

    VerificationCode getVerificationCodeByUser(Long useId) throws Exception ;
    
    Boolean verifyVerificationCode( VerificationCode verificationCode) throws Exception;
    void deleteVerificationCodeById(VerificationCode verificationCode) ;
}
