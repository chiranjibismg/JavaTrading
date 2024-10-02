package com.Chiranjibi.JavaTrading.controller;

import com.Chiranjibi.JavaTrading.domain.VerificationType;
import com.Chiranjibi.JavaTrading.models.ForgotPasswordToken;
import com.Chiranjibi.JavaTrading.models.User;
import com.Chiranjibi.JavaTrading.models.VerificationCode;
import com.Chiranjibi.JavaTrading.request.ForgotPasswordTokenRequest;
import com.Chiranjibi.JavaTrading.request.ResetPasswordRequest;
import com.Chiranjibi.JavaTrading.response.ApiResponse;
import com.Chiranjibi.JavaTrading.response.AuthResponse;
import com.Chiranjibi.JavaTrading.service.EmailService;
import com.Chiranjibi.JavaTrading.service.ForgotPasswordService;
import com.Chiranjibi.JavaTrading.service.UserService;
import com.Chiranjibi.JavaTrading.service.VerificationCodeService;
import com.Chiranjibi.JavaTrading.utils.OtpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class UserController {


    @Autowired
    private UserService userService;

    @Autowired
    private VerificationCodeService verificationCodeService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private ForgotPasswordService forgotPasswordService;


    @GetMapping("/api/users/profile")
    public ResponseEntity<User> getUserProfile(@RequestHeader("Authorization") String jwt) throws Exception {

        User user =userService.findUserProfileByJwt(jwt) ;


        return new ResponseEntity<User> (user, HttpStatus.OK) ;
    }

    @PatchMapping("/api/users/verification/{verificationType}/send-otp")
    public ResponseEntity<String> sendVerificationOtp(@RequestHeader("Authorization") String jwt, @PathVariable VerificationType verificationType) throws Exception {

        User user =userService.findUserProfileByJwt(jwt) ;

        VerificationCode verificationCode=verificationCodeService.getVerificationCodeByUser(user.getId()) ;

        if(verificationCode!=null){

            verificationCode=verificationCodeService.sendVerificationCode(user,verificationType)  ;


        }

        if (verificationType.equals(VerificationType.EMAIL)) {

            assert verificationCode != null;
            emailService.sendVerificationOtpEmail(user.getEmail(), verificationCode.getOtp());


        }




        return new ResponseEntity<> ("Verification OTP has been sent successfully ", HttpStatus.OK) ;
    }

    @PatchMapping("/api/users/enable-two-factor/verify-otp/{otp}")
    public ResponseEntity<User> enabledTwoFactorAuthentication(
            @RequestHeader("Authorization") String jwt,
            @PathVariable String otp
    ) throws Exception {


        User user =userService.findUserProfileByJwt(jwt) ;

        VerificationCode verificationCode=verificationCodeService.getVerificationCodeByUser(user.getId()) ;

        String sendTo = verificationCode.getVerificationType().equals(VerificationType.EMAIL) ? verificationCode.getEmail():verificationCode.getMobile() ;

        boolean isVerified =verificationCode.getOtp().equals(otp);

        if(isVerified){

            User updatedUser =userService.enableTwoFactorAuthentication(verificationCode.getVerificationType(),sendTo,user) ;



            verificationCodeService.deleteVerificationCodeById(verificationCode);

            return new ResponseEntity<>(updatedUser, HttpStatus.OK);

        }




        throw new Exception("Wrong otp");

    }

    @PatchMapping("/auth/users/reset-password/send-otp")
    public ResponseEntity<AuthResponse> sendForgotPasswordOtp( @RequestBody ForgotPasswordTokenRequest req) throws Exception {

        User user =userService.findUserProfileByEmail(req.getSendTo()) ;

        String otp = OtpUtils.generateOtp() ;

        UUID uuid = UUID.randomUUID();
        String id = uuid.toString() ;

        ForgotPasswordToken token = forgotPasswordService.findByUser(user.getId()) ;

        if (token==null){
            token =forgotPasswordService.createToken(user,id,otp,req.getVerificationType(),req.getSendTo()) ;

        }

        if (req.getVerificationType().equals(VerificationType.EMAIL)) {

             emailService.sendVerificationOtpEmail(user.getEmail(), token.getOtp());

        }

        AuthResponse authResponse=new AuthResponse();
        authResponse.setSession(token.getId()) ;
        authResponse.setMessage("Password reset otp successfully");



        return new ResponseEntity<> (authResponse, HttpStatus.OK) ;
    }


    @PatchMapping("/auth/users/reset-password/verify-otp/")
    public ResponseEntity<ApiResponse> resetPassword(
            @RequestParam String id ,
            @RequestBody ResetPasswordRequest req


    ) throws Exception {


        ForgotPasswordToken forgotPasswordToken=forgotPasswordService.findById(id);


        boolean isVerified = forgotPasswordToken.getOtp().equals(req.getOtp()) ;

        if (isVerified){

            userService.updatePassword(forgotPasswordToken.getUser(),req.getPassword()) ;
            ApiResponse res= new ApiResponse() ;

            res.setMessage("Password updated successfully") ;

            return new ResponseEntity<>(res,HttpStatus.ACCEPTED) ;
        }
        throw new Exception("Wrong otp");







    }






}
