package dev.junior.hackathon.librarysystem.request;

import dev.junior.hackathon.librarysystem.model.Genre;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class BookRequest {
    String bookName;
    String author;
    String description;
    MultipartFile bookFile;
    MultipartFile bookCover;
    private Set<Genre> genres;

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MultipartFile getBookFile() {
        return bookFile;
    }

    public void setBookFile(MultipartFile bookFile) {
        this.bookFile = bookFile;
    }

    public MultipartFile getBookCover() {
        return bookCover;
    }

    public void setBookCover(MultipartFile bookCover) {
        this.bookCover = bookCover;
    }
}
