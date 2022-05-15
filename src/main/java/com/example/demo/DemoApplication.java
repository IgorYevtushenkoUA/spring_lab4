package com.example.demo;

import com.example.demo.service.BookService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(DemoApplication.class, args);
        test(applicationContext);
    }


    public static void test(ApplicationContext applicationContext) {
        BookService bookService = applicationContext.getBean(BookService.class);
    }

}
