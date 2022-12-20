package dev.junior.hackathon.librarysystem.security.service;

import dev.junior.hackathon.librarysystem.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
