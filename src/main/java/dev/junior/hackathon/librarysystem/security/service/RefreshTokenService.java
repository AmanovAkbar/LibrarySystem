package dev.junior.hackathon.librarysystem.security.service;


import dev.junior.hackathon.librarysystem.exception.TokenRefreshException;
import dev.junior.hackathon.librarysystem.model.RefreshToken;
import dev.junior.hackathon.librarysystem.repository.RefreshTokenRepository;
import dev.junior.hackathon.librarysystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenService {
    @Value("${dev.junior.hackathon.jwtRefreshExpirationMs}")
    private Long refreshTokenDurationMs;
    @Autowired
    RefreshTokenRepository refreshTokenRepository;
    @Autowired
    UserRepository userRepository;

    public RefreshToken createRefreshToken(Long userId){
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUser(userRepository.findUserById(userId).get());
        refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
        refreshToken.setToken(UUID.randomUUID().toString().replace("-", ""));
        refreshTokenRepository.save(refreshToken);
        return refreshToken;

    }

    public RefreshToken verifyExpiration (RefreshToken token){
        if(token.getExpiryDate().compareTo(Instant.now())<0){
            refreshTokenRepository.delete(token);
            throw new TokenRefreshException(token.getToken(), "Refresh token was expired. Please make a new signin request");
        }
        return token;
    }
    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findRefreshTokenByToken(token);
    }
    @Transactional
    public int deleteByUserId(Long userid){
        return refreshTokenRepository.deleteRefreshTokenByUser(userRepository.findUserById(userid).get());
    }


}