package com.example.demo.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "books")
@Getter
@Setter
@Builder
public class Book{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "page_number")
    private int pageNumber;

    @Column(name = "publisher")
    private String publisher;

    @Column(name = "price")
    private double price;

    @ManyToMany
    @ToString.Exclude
    @JoinTable(
            name = "book_has_genres",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private List<Genre> genres;

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", pageNumber=" + pageNumber +
                ", publisher='" + publisher + '\'' +
                ", price=" + price +
                "}\n";
    }
}

