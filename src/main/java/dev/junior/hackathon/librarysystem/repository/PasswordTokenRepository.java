package dev.junior.hackathon.librarysystem.repository;

import dev.junior.hackathon.librarysystem.model.PasswordToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PasswordTokenRepository extends JpaRepository<PasswordToken, Long> {
    Optional<PasswordToken> findPasswordTokenById(long id);
    Optional<PasswordToken> findPasswordTokenByEmail(String email);
    Optional<PasswordToken> findPasswordTokenByToken(String token);

}
