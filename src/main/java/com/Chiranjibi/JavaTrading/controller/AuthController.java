package com.Chiranjibi.JavaTrading.controller;

import com.Chiranjibi.JavaTrading.config.JwtProvider;
import com.Chiranjibi.JavaTrading.models.TwoFactorOTP;
import com.Chiranjibi.JavaTrading.models.User;
import com.Chiranjibi.JavaTrading.repository.UserRepository;
import com.Chiranjibi.JavaTrading.response.AuthResponse;
import com.Chiranjibi.JavaTrading.service.CustomUserDetailsService;
import com.Chiranjibi.JavaTrading.service.EmailService;
import com.Chiranjibi.JavaTrading.service.TwoFactorOtpService;
import com.Chiranjibi.JavaTrading.utils.OtpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private TwoFactorOtpService twoFactorOtpService;

    @Autowired
    private EmailService emailService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> register(@RequestBody User user) throws Exception {
//        System.out.println("Received User: " + user);


        User isEmailExist = userRepository.findByEmail(user.getEmail());
        if (isEmailExist != null){
            throw new Exception("Email is already registered") ;
        }
        User newUser =new User();

        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());
        newUser.setFullName(user.getFullName());

        User savedUser = userRepository.save(newUser);

        Authentication authentication =new UsernamePasswordAuthenticationToken(
                user.getEmail(),
                user.getPassword()

        );

        SecurityContextHolder.getContext().setAuthentication(authentication);


        String jwt = JwtProvider.generatetoken(authentication);






       AuthResponse res=new AuthResponse() ;
       res.setJwt(jwt);
       res.setStatus(true);
       res.setMessage("Register is successful") ;




        return new ResponseEntity<>(res, HttpStatus.CREATED) ;



    }

    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> login(@RequestBody User user) throws Exception {



        String username = user.getEmail();
        String password = user.getPassword();


        Authentication auth =authenticate(username,password) ;


        SecurityContextHolder.getContext().setAuthentication(auth);


        String jwt = JwtProvider.generatetoken(auth);

        User authuser =userRepository.findByEmail(username) ;



        if(user.getTwoFactorAuth().isEnabled()){

            AuthResponse res=new AuthResponse() ;
            res.setMessage("Two factor Auth is enabled") ;
            res.setTwoFactorAuthEnabled(true);

            String otp= OtpUtils.generateOtp() ;

            TwoFactorOTP oldTwoFactorOTP =twoFactorOtpService.findByUser(authuser.getId()) ;

            if(oldTwoFactorOTP!=null){

                twoFactorOtpService.deleteTwoFactorOtp(oldTwoFactorOTP);
            }

            TwoFactorOTP newTwoFactorOTP =twoFactorOtpService.createTwoFactorOtp(authuser,otp,jwt) ;

            emailService.sendVerificationOtpEmail(username,otp);
            res.setSession(newTwoFactorOTP.getId());

            return new ResponseEntity<>(res, HttpStatus.ACCEPTED) ;

        }


        AuthResponse res=new AuthResponse() ;
        res.setJwt(jwt);
        res.setStatus(true);
        res.setMessage("Login is successful") ;




        return new ResponseEntity<>(res, HttpStatus.CREATED) ;



    }

    private Authentication authenticate(String username, String password) {

        UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

        if (userDetails==null){
            throw new BadCredentialsException("Invalid Username") ;
        }

        if(!password.equals(userDetails.getPassword())){
            throw  new BadCredentialsException("Invalid Password") ;

        }


        return new UsernamePasswordAuthenticationToken(userDetails,password,userDetails.getAuthorities());

    }

    @PostMapping("/two-factor/otp/{otp}")
    public ResponseEntity<AuthResponse> verifySignInOtp(
            @PathVariable String otp,
            @RequestParam String id) throws Exception {


        TwoFactorOTP  twoFactorOTP =twoFactorOtpService.findById(id)  ;


        if (twoFactorOtpService.verifyTwoFactorOtp(twoFactorOTP,otp)){

            AuthResponse res=new AuthResponse() ;

            res.setMessage("Two factor Authentication is verified") ;

            res.setTwoFactorAuthEnabled(true);
            res.setSession(twoFactorOTP.getJwt());

            return new ResponseEntity<>(res, HttpStatus.OK) ;

        }

        throw new Exception("Wrong otp ") ;


//        return null ;
    }


}
