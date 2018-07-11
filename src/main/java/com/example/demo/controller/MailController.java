package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mailController")
public class MailController {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String Sender;
//    @RequestMapping("/sendSimpleMail")
//    public void sendSimpleMail() throws Exception{
//        SimpleMailMessage mailMessage = new SimpleMailMessage();
//        mailMessage.setFrom(Sender);
//        mailMessage.setTo(Sender);
//        mailMessage.setSubject("Test");
//        mailMessage.setText("Test");
//        mailSender.send(mailMessage);
//        System.out.println("mail lalala");
//    }


}
