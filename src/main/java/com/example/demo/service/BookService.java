package com.example.demo.service;

import com.example.demo.entity.Book;
import com.example.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BookService {

    @Autowired
    BookRepository bookRepo;

    public List<Book> getBooks() {
        return bookRepo.findAll();
    }

    public void createBook(String name, String description, int pageNum, String publisher, double price) {

        Book newBook = Book.builder()
                .name(name)
                .description(description)
                .pageNumber(pageNum)
                .publisher(publisher)
                .price(price)
                .build();

        bookRepo.save(newBook);
    }
}
