package com.usm.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(String to,String object,String message){
        SimpleMailMessage mailMessage=new SimpleMailMessage();
        mailMessage.setTo(to);
        mailMessage.setSubject(object);
        mailMessage.setText(message);
        javaMailSender.send(mailMessage);
    }
}
