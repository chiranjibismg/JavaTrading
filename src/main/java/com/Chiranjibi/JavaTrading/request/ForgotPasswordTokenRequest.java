package com.Chiranjibi.JavaTrading.request;


import com.Chiranjibi.JavaTrading.domain.VerificationType;
import lombok.Data;

@Data
public class ForgotPasswordTokenRequest {

    private String sendTo ;
    private VerificationType verificationType ;

}
