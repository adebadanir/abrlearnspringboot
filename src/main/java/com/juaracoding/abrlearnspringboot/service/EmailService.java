package com.juaracoding.abrlearnspringboot.service;

import com.juaracoding.abrlearnspringboot.model.EmailDetails;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.EnableAsync;


public interface EmailService  {
    // Method
    // To send a simple email
    String sendSimpleMail(EmailDetails details);

}
