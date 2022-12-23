package dev.junior.hackathon.librarysystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import dev.junior.hackathon.librarysystem.model.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByUsername(String username);
    Boolean existsByUsername(String username);

    Optional<User>findUserById(long id);

    Optional<User> findUserByEmail(String email);
    Boolean existsByEmail(String email);
}
