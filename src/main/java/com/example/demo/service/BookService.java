package com.example.demo.service;

import com.example.demo.entity.Book;
import com.example.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepo;

    @Transactional
    public List<Book> getBooks() {
        return bookRepo.findAll();
    }

    public Book increasePrice(int id, double percent) {
        Book book = bookRepo.findById(id).orElse(new Book());
        book.setPrice(book.getPrice() * (1 + percent / 100));
        book = bookRepo.save(book);
        return book;
    }

    private void updatePrice(int bookId, double newPrice) {
        Book book = bookRepo.findById(bookId).orElse(new Book());
        book.setPrice(newPrice);
        bookRepo.save(book);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void requiredUpdatePrice(int id, double price) {
        updatePrice(id, price);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void supportsUpdatePrice(int id, double price) {
        updatePrice(id, price);
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public void mandatoryUpdatePrice(int id, double price) {
        updatePrice(id, price);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void requiresNewUpdatePrice(int id, double price) {
        updatePrice(id, price);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void notSupportedUpdatePrice(int id, double price) {
        updatePrice(id, price);
    }

    @Transactional(propagation = Propagation.NEVER)
    public void neverUpdatePrice(int id, double price) {
        updatePrice(id, price);
    }

    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public void readUncommitted(int id, double price) {
        updatePrice(id, price);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void readCommitted(int id, double price) {
        updatePrice(id, price);
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void repeatableRead(int id, double price) {
        updatePrice(id, price);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void serializable(int id, double price) {
        updatePrice(id, price);
    }

}
