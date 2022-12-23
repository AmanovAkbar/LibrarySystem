package dev.junior.hackathon.librarysystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name="genres")
@Data
@AllArgsConstructor
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    @Enumerated(EnumType.STRING)
    private EGenre name;

    public Genre() {

    }
}
