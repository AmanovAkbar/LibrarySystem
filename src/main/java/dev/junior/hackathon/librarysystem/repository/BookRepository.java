package dev.junior.hackathon.librarysystem.repository;

import dev.junior.hackathon.librarysystem.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findBookByBookName(String bookName);
}
