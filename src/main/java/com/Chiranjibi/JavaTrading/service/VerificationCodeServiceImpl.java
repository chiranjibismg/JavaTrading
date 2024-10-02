package com.Chiranjibi.JavaTrading.service;

import com.Chiranjibi.JavaTrading.domain.VerificationType;
import com.Chiranjibi.JavaTrading.models.User;
import com.Chiranjibi.JavaTrading.models.VerificationCode;
import com.Chiranjibi.JavaTrading.repository.VerificationCodeRepository;
import com.Chiranjibi.JavaTrading.utils.OtpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class VerificationCodeServiceImpl implements VerificationCodeService {


    @Autowired
    private VerificationCodeRepository verificationCodeRepository;

    @Override
    public VerificationCode sendVerificationCode(User user, VerificationType verificationType) {

        VerificationCode verificationCode1 = new VerificationCode();
        verificationCode1.setOtp(OtpUtils.generateOtp());
        verificationCode1.setVerificationType(verificationType);
        verificationCode1.setUser(user);

        return verificationCodeRepository.save(verificationCode1);
    }

    @Override
    public VerificationCode getVerificationCodeById(Long id) throws Exception {

        Optional<VerificationCode> verificationCode = verificationCodeRepository.findById(id);

        if (verificationCode.isPresent()) {
            return verificationCode.get();
        }
        throw new Exception("Verification code not found") ;

    }

    @Override
    public VerificationCode getVerificationCodeByUser(Long useId) {

        return verificationCodeRepository.findByUserId(useId);
    }

    @Override
    public Boolean verifyVerificationCode(VerificationCode verificationCode) throws Exception {
        return null;
    }

    @Override
    public void deleteVerificationCodeById(VerificationCode verificationCode) {

        verificationCodeRepository.delete(verificationCode);

    }
}
