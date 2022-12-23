package dev.junior.hackathon.librarysystem.service;

import dev.junior.hackathon.librarysystem.model.User;
import dev.junior.hackathon.librarysystem.repository.UserRepository;
import dev.junior.hackathon.librarysystem.security.jwt.response.ResponseMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

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
    @Transactional
    public ResponseEntity<ResponseMessage> uploadPfp(@RequestParam("pfpfile")MultipartFile pfp) throws IOException {
        String fileLocation = new File("src\\main\\resources\\images\\pfp").getAbsolutePath() + "\\" + pfp.getOriginalFilename();
        FileOutputStream output = new FileOutputStream(fileLocation);
        output.write(pfp.getBytes());
        output.close();
        return ResponseEntity.ok(new ResponseMessage("Successfully uploaded!"));
    }
}
