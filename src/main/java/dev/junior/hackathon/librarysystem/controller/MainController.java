package dev.junior.hackathon.librarysystem.controller;

import dev.junior.hackathon.librarysystem.model.Book;
import dev.junior.hackathon.librarysystem.model.Genre;
import dev.junior.hackathon.librarysystem.model.User;
import dev.junior.hackathon.librarysystem.repository.UserRepository;
import dev.junior.hackathon.librarysystem.security.jwt.response.ResponseMessage;
import dev.junior.hackathon.librarysystem.service.BookService;
import dev.junior.hackathon.librarysystem.service.GenreService;
import dev.junior.hackathon.librarysystem.security.service.UserDetailsServiceImpl;
import dev.junior.hackathon.librarysystem.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;


//This controller is for checking, can be modified/deleted later.

@RestController
public class MainController {
    private BookService bookService;
    private UserRepository userRepository;
    private UserService userService;
    private GenreService genreService;
    public MainController(BookService bookService, UserRepository userRepository,UserService userService,GenreService genreService){
        this.bookService = bookService;
        this.userRepository = userRepository;
        this.userService = userService;
        this.genreService= genreService;
    }

    @GetMapping("/")
    public String HelloWorld(){
        return "Hello, World!";
    }
    @GetMapping("/user")
    public ResponseEntity<ResponseMessage> helloUser(){
        return ResponseEntity.ok().body(new ResponseMessage("Hello, User!"));
    }
    @GetMapping("/user/personal")
    public ResponseEntity<User> getUserByUsername (Principal principal) {
        User user = userRepository.findUserByUsername(principal.getName()).orElseThrow(()->new UsernameNotFoundException("No user with that name"));
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    @PutMapping("/user/personal/edit")
    public ResponseEntity<User> editUser(@RequestBody User user) {
        user.setId(user.getId());
        userService.updateUser(user);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }
    @GetMapping("/admin")
    public ResponseEntity<ResponseMessage> helloAdmin(){
        return ResponseEntity.ok().body(new ResponseMessage("Hello, Admin!"));
    }

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getBooks(){
        return new ResponseEntity<>(bookService.listBooks(),HttpStatus.OK);
    }
    @PostMapping("/books/create")
    public ResponseEntity<Book> createBook(@Valid @RequestBody Book book) {
        bookService.createBook(book);
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }


    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable(name = "id") Long id){
        return new ResponseEntity<>(bookService.getBook(id),HttpStatus.OK);
    }

    @DeleteMapping("/books/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<HttpStatus> deleteBookById(@PathVariable(name = "id") Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @GetMapping("/books/genres")
    public ResponseEntity<List<Genre>> getGenres(){
        return new ResponseEntity<>(genreService.listGenres(),HttpStatus.OK);
    }
    @GetMapping("/books/genres/{id}")
    public ResponseEntity<List<Book>> getBooksByJanre(@PathVariable(name = "id") Long id){
        Genre genre = genreService.getGenreById(id);
        return new ResponseEntity<>(bookService.getBooksByGenre(genre),HttpStatus.OK);
    }
}
