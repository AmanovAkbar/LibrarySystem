package dev.junior.hackathon.librarysystem.service;

import dev.junior.hackathon.librarysystem.model.User;
import dev.junior.hackathon.librarysystem.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    private UserRepository userRepository;
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Transactional
    public void updateUser(User user) throws UsernameNotFoundException {
        User old = userRepository.findUserByUsername(user.getUsername()).orElseThrow(() -> new UsernameNotFoundException("No user with that name"));
        user.setId(old.getId());
        userRepository.save(user);
    }
}
