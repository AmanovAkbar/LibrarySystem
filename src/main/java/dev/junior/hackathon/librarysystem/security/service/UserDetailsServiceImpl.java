package dev.junior.hackathon.librarysystem.security.service;


import dev.junior.hackathon.librarysystem.model.User;
import dev.junior.hackathon.librarysystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
        return UserDetailsImpl.build(user);
    }

    @Transactional
    public void updateUser(User user) throws UsernameNotFoundException{
        User old = userRepository.findUserByUsername(user.getUsername()).orElseThrow(() -> new UsernameNotFoundException("No user with that name"));
        user.setId(old.getId());
        userRepository.save(user);
    }
}
