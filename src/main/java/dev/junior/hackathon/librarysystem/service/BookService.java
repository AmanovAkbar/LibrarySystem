package dev.junior.hackathon.librarysystem.service;

import dev.junior.hackathon.librarysystem.model.Book;
import dev.junior.hackathon.librarysystem.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookService {
    private final BookRepository bookRepository;
    @Autowired

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> listBooks() {
        return bookRepository.findAll();
    }

//

    @Transactional
    public void create(Book book) {
        bookRepository.save(book);
    }

    /***
     * Получить пользователя по ID
     * @param id ID пользователя
     * @return пользователь либо null
     */
    public Book getBook(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
