package dev.junior.hackathon.librarysystem.security.service;

import dev.junior.hackathon.librarysystem.security.jwt.response.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {
    @Autowired
    private JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String sender;

    public ResponseMessage sendEmail(String recipient, String subject, String message){
        try{
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(sender);
            mailMessage.setSubject(subject);
            mailMessage.setTo(recipient);
            mailMessage.setText(message);
            javaMailSender.send(mailMessage);
            return new ResponseMessage("Password reset email was sent!");
        }catch (Exception e){
            return new ResponseMessage("There was a mistake while sending you an email!");
        }
    }
}
