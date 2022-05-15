package com.example.demo.repository;

import com.example.demo.entity.Genre;
import com.example.demo.initializer.Postgres;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class GenreRepositoryJDBCTemplateTest extends Postgres {

    private static final String INSERT_SQL = "insert into genres(id,name) values (?,?)";
    private static final String DELETE_BY_ID_SQL = "delete from genres where id = ? ";
    private static final String DELETE_BY_NAME_SQL = "delete from genres where name = ? ";
    private static final String UPDATE_SQL = "update genres set name = ? where id = ?";
    private static final String SELECT_ALL_SQL = "select * from genres ";
    private static final String SELECT_BY_ID_SQL = "select * from genres g where g.id = ?";

    class GenreRowMapper implements RowMapper<Genre> {

        @Override
        public Genre mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Genre.builder()
                    .id(rs.getInt("id"))
                    .name(rs.getString("name"))
                    .build();
        }
    }

    @Autowired
    JdbcOperations jdbcOperations;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @BeforeEach
    void initializeBD() {
        List<Genre> genres = getAllGenres();
        if (genres.size() == 0) {
            jdbcOperations.update(INSERT_SQL, 1, "genre1");
            jdbcOperations.update(INSERT_SQL, 2, "genre2");
            jdbcOperations.update(INSERT_SQL, 3, "genre3");
        }
    }

    @AfterEach
    void deleteDB() {
        List<Genre> genres = getAllGenres();
        for (Genre g : genres)
            jdbcTemplate.update(DELETE_BY_ID_SQL, g.getId());
    }

    @Test
    void should_return_correct_size() {

        List<Genre> genres = getAllGenres();
        Assert.assertEquals(3, genres.size());
    }


    @Test
    void should_return_updated_genre() {
        jdbcTemplate.update(UPDATE_SQL,
                "defaultNewGenreName", 1);
        Genre genre = findById(1);

        Assert.assertEquals("defaultNewGenreName", genre.getName());
    }


    @Test
    void delete_all_genres() {
        List<Genre> genres = getAllGenres();
        for (Genre g : genres)
            jdbcTemplate.update(DELETE_BY_NAME_SQL, g.getName());
        Assert.assertEquals(0, getAllGenres().size());
    }

    private List<Genre> getAllGenres() {
        return jdbcTemplate.query(
                SELECT_ALL_SQL,
                new GenreRowMapper());
    }

    private Genre findById(int id) {
        return jdbcTemplate.queryForObject(SELECT_BY_ID_SQL, new Object[]{id}, new GenreRowMapper());
    }

}
