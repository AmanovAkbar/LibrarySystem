package dev.junior.hackathon.librarysystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="books")
@Data
@AllArgsConstructor
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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(	name = "book_genres",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private Set<Genre> genres = new HashSet<>();


    // должны быть поля для фоток и для файлов
    public Book() {
    }
}
