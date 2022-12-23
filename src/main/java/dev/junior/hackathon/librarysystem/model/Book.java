package dev.junior.hackathon.librarysystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column
    private String bookName;
    @Column
    private String author;
    @Column
    private Date date;
    @Column(columnDefinition = "text")
    private String description;
    @Column
    @Lob
    @JsonIgnore
    private byte [] data;
    @Column
    private String fileName;
    @Column
    String photoname;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(	name = "book_genres",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private Set<Genre> genres = new HashSet<>();


    public Book(Long id, String bookName, String author, Date date, String description, byte[] data, String photoname, Set<Genre> genres) {
        this.id = id;
        this.bookName = bookName;
        this.author = author;
        this.date = date;
        this.description = description;
        this.data = data;
        this.photoname = photoname;
        this.genres = genres;
    }

    public Book() {
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getPhotoname() {
        return photoname;
    }

    public void setPhotoname(String photoname) {
        this.photoname = photoname;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }
}
