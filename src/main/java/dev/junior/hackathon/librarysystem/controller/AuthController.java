package dev.junior.hackathon.librarysystem.controller;


import dev.junior.hackathon.librarysystem.security.jwt.request.RequestRefreshToken;
import dev.junior.hackathon.librarysystem.security.jwt.response.ResponseJwt;
import dev.junior.hackathon.librarysystem.security.jwt.response.ResponseMessage;
import dev.junior.hackathon.librarysystem.security.jwt.response.ResponseRefreshToken;
import dev.junior.hackathon.librarysystem.security.request.RequestLogin;
import dev.junior.hackathon.librarysystem.security.request.RequestSignup;
import dev.junior.hackathon.librarysystem.security.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthService authService;


    @PostMapping("/login")
    public ResponseEntity<ResponseJwt> authenticateUser(@Valid @RequestBody RequestLogin requestLogin){
        return authService.authenticateUser(requestLogin);
    }
    @PostMapping("/register")
    public ResponseEntity<ResponseMessage>registerUser(@Valid @RequestBody RequestSignup requestSignup){
        return authService.registerUser(requestSignup);
    }


    @PostMapping("/refreshtoken")
    public ResponseEntity<ResponseRefreshToken> refreshToken(@Valid @RequestBody RequestRefreshToken requestRefreshToken){
        return authService.refreshToken(requestRefreshToken);
    }
}
