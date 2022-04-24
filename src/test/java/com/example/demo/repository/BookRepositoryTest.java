package com.example.demo.repository;

import com.example.demo.entity.Book;
import com.example.demo.initializer.Postgres;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BookRepositoryTest extends Postgres {

    @Autowired
    protected BookRepository bookRepository;

    protected void createBook(String name, String description, int pageNum, String publisher, double price) {
        Book newBook = Book.builder()
                .name(name)
                .description(description)
                .pageNumber(pageNum)
                .publisher(publisher)
                .price(price)
                .build();

        bookRepository.save(newBook);
    }

    @BeforeEach
    public void initializeBD() {
        createBook("name1", "description", 100, "publisher", 1.0);
    }

    @AfterEach
    public void deleteDB() {
        bookRepository.deleteAll();
    }

    @Test
    void contextLoads() {
        List<Book> books = bookRepository.findAll();
        Assert.assertEquals(books.size(), 1);
    }

    @Test
    void nameSearching() {
        String defaultName = "defaultName";
        List<Book> books = bookRepository.findAllByName(defaultName);
        Assert.assertEquals(0, books.size());
    }

    @Test
    void getBookByIdNotNull() {
        int defaultId = 1;
        Book book = bookRepository.findById(defaultId).orElse(null);
        Assert.assertNotNull(book);
    }

    @Test
    void getBookByIdNull() {
        int defaultId = 0;
        Book book = bookRepository.findById(defaultId).orElse(null);
        Assert.assertNull(book);
    }


}
