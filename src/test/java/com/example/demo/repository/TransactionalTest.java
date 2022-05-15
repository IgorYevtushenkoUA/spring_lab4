package com.example.demo.repository;

import com.example.demo.entity.Book;
import com.example.demo.service.BookService;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.IllegalTransactionStateException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@SpringBootTest
public class TransactionalTest {

    @Autowired
    BookService bookService;

    @Autowired
    private EntityManagerFactory emf;


    private final int defaultBookPrice = 0;
    private final double specialPrice = 777.0;

    @AfterEach
    void setDefaultBookPrice() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Book book = em.find(Book.class, 1);
        book.setPrice(defaultBookPrice);
        em.getTransaction().commit();
        em.close();
    }

    @Test
    void testRequired() {
        EntityManager em = emf.createEntityManager();
        bookService.requiredUpdatePrice(1, specialPrice);
        Book book = em.find(Book.class, 1);
        Assert.assertEquals(777.0, book.getPrice(), 0);
    }

    @Test
    void testSupports() {
        EntityManager em = emf.createEntityManager();
        bookService.supportsUpdatePrice(1, specialPrice);
        Book book = em.find(Book.class, 1);
        Assert.assertEquals(777.0, book.getPrice(), 0);
    }

    @Test
    void testMandatory() {
        Assert.assertThrows(IllegalTransactionStateException.class, () -> {
            EntityManager em = emf.createEntityManager();
            bookService.mandatoryUpdatePrice(1, specialPrice);
        });

    }

    @Test
    void testRequiresNew() {
        EntityManager em = emf.createEntityManager();
        bookService.requiresNewUpdatePrice(1, specialPrice);
        Book book = em.find(Book.class, 1);
        Assert.assertEquals(777.0, book.getPrice(), 0);

    }

    @Test
    void testNotSupported() {
        EntityManager em = emf.createEntityManager();
        bookService.notSupportedUpdatePrice(1, specialPrice);
        Book book = em.find(Book.class, 1);
        Assert.assertEquals(777.0, book.getPrice(), 0);

    }

    @Test
    void testNever() {
        EntityManager em = emf.createEntityManager();
        bookService.neverUpdatePrice(1, specialPrice);
        Book book = em.find(Book.class, 1);
        Assert.assertEquals(777.0, book.getPrice(), 0);
    }

}
