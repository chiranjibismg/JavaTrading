package com.Chiranjibi.JavaTrading.controller;

import com.Chiranjibi.JavaTrading.models.User;
import com.Chiranjibi.JavaTrading.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody User user) throws Exception {
//        System.out.println("Received User: " + user);


        User isEmailexist = userRepository.findByEmail(user.getEmail());
        if (isEmailexist != null){
            throw   new Exception("Email is already registered") ;
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







        return new ResponseEntity<>(savedUser, HttpStatus.CREATED) ;



    }
}
