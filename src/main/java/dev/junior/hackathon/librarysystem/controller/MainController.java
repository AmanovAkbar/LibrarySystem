package dev.junior.hackathon.librarysystem.controller;

import dev.junior.hackathon.librarysystem.model.Book;
import dev.junior.hackathon.librarysystem.model.User;
import dev.junior.hackathon.librarysystem.repository.UserRepository;
import dev.junior.hackathon.librarysystem.security.jwt.response.ResponseMessage;
import dev.junior.hackathon.librarysystem.security.service.BookService;
import dev.junior.hackathon.librarysystem.security.service.UserDetailsServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;


//This controller is for checking, can be modified/deleted later.

@RestController
public class MainController {
    private BookService bookService;
    private UserRepository userRepository;
    private UserDetailsServiceImpl userService;
    public MainController(BookService bookService, UserRepository userRepository,UserDetailsServiceImpl userService){
        this.bookService = bookService;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping("/")
    public String HelloWorld(){
        return "Hello, World!";
    }
    @GetMapping("/user")
    public ResponseEntity<ResponseMessage> helloUser(){
        return ResponseEntity.ok().body(new ResponseMessage("Hello, User!"));
    }
    @GetMapping("/personal")
    public ResponseEntity<Optional<User>> getUserByUsername (Principal principal) {
        return new ResponseEntity<>(userRepository.findUserByUsername(principal.getName()), HttpStatus.OK);
    }
    @PutMapping("/personal/edit")
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
    public ResponseEntity<HttpStatus> deleteBookById(@PathVariable(name = "id") Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
//    @GetMapping("/books/genre/{id}")
//    public ResponseEntity<List<Book>> getBookByJanre(){
//        return new ResponseEntity<>()
//    }
}
