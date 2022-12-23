package dev.junior.hackathon.librarysystem.security.service;


import dev.junior.hackathon.librarysystem.model.PasswordToken;
import dev.junior.hackathon.librarysystem.model.User;
import dev.junior.hackathon.librarysystem.repository.PasswordTokenRepository;
import dev.junior.hackathon.librarysystem.repository.UserRepository;
import dev.junior.hackathon.librarysystem.security.jwt.response.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PasswordService {
    @Autowired
    MailService mailService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    PasswordTokenRepository passwordTokenRepository;
    @Autowired
    UserRepository userRepository;

    public ResponseEntity<ResponseMessage> sendPasswordResetEmail(String email){
        User user = userRepository.findUserByEmail(email)
                .orElseThrow(()->new RuntimeException("Error: This email is not registered!"));
        String token = generateToken();
        PasswordToken passwordToken = new PasswordToken(token, user);
        passwordTokenRepository.save(passwordToken);
        String message = "Reset your password with the link bellow \n " +
                "http://localhost:8080/reset/" +
                token;
        mailService.sendEmail(email, "Password reset", message);
        return  ResponseEntity.ok().body(new ResponseMessage("Password reset email was sent!"));
    }
    public ResponseEntity<ResponseMessage> resetPassword(String token, String password){
        PasswordToken passwordToken = passwordTokenRepository.findPasswordTokenByToken(token)
                .orElseThrow(()->new RuntimeException("Wrong password reset token!"));
        if(passwordToken.isExpired()){
            return ResponseEntity.badRequest().body(new ResponseMessage("Password token is expired!"));
        }
        User user = userRepository.findUserByEmail(passwordToken.getEmail())
                .orElseThrow(()-> new RuntimeException("Error: This email is not registered!"));
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
        passwordTokenRepository.delete(passwordToken);

        return ResponseEntity.ok().body(new ResponseMessage("Password was successfully reset!"));

    }

    public String generateToken(){
        return UUID.randomUUID().toString().replace("-", "");
    }

}
