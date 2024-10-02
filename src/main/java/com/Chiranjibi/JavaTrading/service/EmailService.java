package com.Chiranjibi.JavaTrading.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private JavaMailSender mailSender;

    public void sendVerificationOtpEmail(String email, String otp) throws MessagingException {

         MimeMessage mimeMessage = mailSender.createMimeMessage();
         MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,"utf-8");

         String subject = "You OTP for Chiranjibi Trading APP";
         String text ="Your verfication code is  " +otp ;

         mimeMessageHelper.setSubject(subject);

         mimeMessageHelper.setText(text);
         mimeMessageHelper.setTo(email);

         try{
             mailSender.send(mimeMessage);
         }
         catch (MailException e)
         {
             throw new MessagingException(e.getMessage());
         }

    }
}
