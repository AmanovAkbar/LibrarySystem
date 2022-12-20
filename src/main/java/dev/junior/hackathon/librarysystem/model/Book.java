package dev.junior.hackathon.librarysystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

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
    private String genre;
    @Column
    private Date date;
    @Column(columnDefinition = "text")
    private String description;



    // должны быть поля для фоток и для файлов
    public Book() {
    }
}
