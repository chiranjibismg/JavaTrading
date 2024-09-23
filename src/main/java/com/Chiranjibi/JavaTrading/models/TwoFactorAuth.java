package com.Chiranjibi.JavaTrading.models;


import com.Chiranjibi.JavaTrading.domain.VerificationType;
import lombok.Data;

@Data
public class TwoFactorAuth {

    private boolean isEnabled=false;

    private VerificationType sendTo  ;
}
