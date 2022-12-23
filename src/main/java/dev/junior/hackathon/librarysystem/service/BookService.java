package dev.junior.hackathon.librarysystem.service;

import dev.junior.hackathon.librarysystem.model.Book;
import dev.junior.hackathon.librarysystem.model.Genre;
import dev.junior.hackathon.librarysystem.repository.BookRepository;
import dev.junior.hackathon.librarysystem.request.BookRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookService {
    private BookRepository bookRepository;
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> listBooks() {
        return bookRepository.findAll();
    }

//

    @Transactional
    public Book createBook(BookRequest bookRequest) throws IOException {

        Book book = new Book();
        book.setBookName(bookRequest.getBookName());
        book.setAuthor(bookRequest.getAuthor());
        book.setData(bookRequest.getBookFile().getBytes());
        book.setDate(Date.from(Instant.now()));
        book.setGenres(bookRequest.getGenres());
        book.setFileName(bookRequest.getBookFile().getOriginalFilename());
        MultipartFile coverFile = bookRequest.getBookCover();
        String fileLocation = new File("src\\main\\resources\\images\\covers").getAbsolutePath() + "\\" + coverFile.getOriginalFilename();
        FileOutputStream output = new FileOutputStream(fileLocation);
        output.write(coverFile.getBytes());
        output.close();
        book.setFileName(fileLocation);
        bookRepository.save(book);
        return book;
    }
    public ResponseEntity<byte[]>downloadBook(Long id){
        Optional<Book> book = bookRepository.findById(id);
        Book b;
        if(book.isPresent()){
            b = book.get();
        }else{
            throw new RuntimeException("There is no such a book!");
        }
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + b.getFileName() + "\"").body(b.getData());
    }
    public Book getBook(Long id) {
        return bookRepository.findById(id).orElse(null);
    }


    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    public List<Book> getBooksByGenre(Genre genre){
        return bookRepository.findAll().stream()
                .filter(i->i.getGenres().contains(genre)).toList();
    }
}
