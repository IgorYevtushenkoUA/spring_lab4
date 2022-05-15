package com.example.demo.service;

import com.example.demo.entity.Book;
import com.example.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

}
