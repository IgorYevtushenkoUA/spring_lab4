package com.example.demo.repository;

import com.example.demo.entity.Book;
import com.example.demo.initializer.Postgres;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BookRepositoryTest extends Postgres {

    @Autowired
    protected BookRepository bookRepository;

    @Test
    void should_return_correct_size() {
        List<Book> books = bookRepository.findAll();
        Assert.assertEquals(books.size(), 3);
    }

    @Test
    void should_return_correct_size_when_not_found_book_by_name() {
        String defaultName = "defaultName";
        List<Book> books = bookRepository.findAllByName(defaultName);
        Assert.assertEquals(0, books.size());
    }

    @Test
    void should_return_null_NOT_NULL_when_book_exists_by_id() {
        int defaultId = 1;
        Book book = bookRepository.findById(defaultId).orElse(null);
        Assert.assertNotNull(book);
    }

    @Test
    void should_return_null_NULL_when_book_exists_by_id() {
        int defaultId = 0;
        Book book = bookRepository.findById(defaultId).orElse(null);
        Assert.assertNull(book);
    }


}
