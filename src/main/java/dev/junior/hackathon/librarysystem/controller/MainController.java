package dev.junior.hackathon.librarysystem.controller;

import dev.junior.hackathon.librarysystem.security.jwt.response.ResponseMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


//This controller is for checking, can be modified/deleted later.

@RestController
public class MainController {
    @GetMapping("/")
    public String HelloWorld(){
        return "Hello, World!";
    }
    @GetMapping("/user")
    public ResponseEntity<ResponseMessage> helloUser(){
        return ResponseEntity.ok().body(new ResponseMessage("Hello, User!"));
    }

    @GetMapping("/admin")
    public ResponseEntity<ResponseMessage> helloAdmin(){
        return ResponseEntity.ok().body(new ResponseMessage("Hello, Admin!"));
    }
}
