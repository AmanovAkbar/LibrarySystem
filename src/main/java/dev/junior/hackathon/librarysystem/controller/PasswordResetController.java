package dev.junior.hackathon.librarysystem.controller;

import dev.junior.hackathon.librarysystem.security.jwt.response.ResponseMessage;
import dev.junior.hackathon.librarysystem.security.request.RequestPasswordReset;
import dev.junior.hackathon.librarysystem.security.service.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PasswordResetController {
    @Autowired
    PasswordService passwordService;
    @PostMapping("/reset")
    public ResponseEntity<ResponseMessage> sendPasswordResetEmail(@RequestParam(defaultValue = "none") String email){
        if(email.equals("none")){
            return ResponseEntity.badRequest().body(new ResponseMessage("Email is empty!"));
        }
        return passwordService.sendPasswordResetEmail(email);
    }

    @PostMapping("/reset/{token}")
    public ResponseEntity<ResponseMessage> resetPassword(@PathVariable String token,
                                                         @RequestBody RequestPasswordReset requestPasswordReset){
        if(requestPasswordReset.getPassword().equals(requestPasswordReset.getSecondPassword())){
            return passwordService.resetPassword(token, requestPasswordReset.getPassword());
        }else{
            return ResponseEntity.badRequest().body(new ResponseMessage("Passwords doesn't match!"));
        }
    }
}
