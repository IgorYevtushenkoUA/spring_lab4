package com.example.demo.repository;

import com.example.demo.initializer.Postgres;
import org.springframework.beans.factory.annotation.Autowired;

public class GenreRepositoryTest extends Postgres {

    @Autowired
    protected GenreRepository genreRepository;


}
