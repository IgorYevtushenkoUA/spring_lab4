package com.example.demo.initializer;

import com.example.demo.entity.Book;
import com.example.demo.entity.Genre;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.GenreRepository;
import lombok.Builder;
import lombok.experimental.UtilityClass;
import org.flywaydb.core.internal.database.postgresql.PostgreSQLConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.util.SocketUtils;
import org.testcontainers.containers.PostgreSQLContainer;

@SpringBootTest
@ContextConfiguration(initializers = {Postgres.Initializer.class})

//@UtilityClass

public class Postgres {




    public static final PostgreSQLContainer<?> container = new PostgreSQLContainer<>("postgres:14.2")
            .withUsername("postgres")
            .withPassword("admin")
            .withDatabaseName("lab4");

    static {
        container.start();
    }

    public static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        @Override
        public void initialize(ConfigurableApplicationContext applicationContext) {
            TestPropertyValues.of(
                    "spring.datasource.url=" + container.getJdbcUrl(),
                    "spring.datasource.username=" + container.getUsername(),
                    "spring.datasource.password=" + container.getPassword(),
                    "local.server.ports=" + SocketUtils.findAvailableTcpPort()
            ).applyTo(applicationContext.getEnvironment());
        }
    }



}
