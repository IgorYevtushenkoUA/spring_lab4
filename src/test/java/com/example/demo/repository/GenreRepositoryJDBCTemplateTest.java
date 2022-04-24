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
    private static final String DELETE_BY_NAME_SQL = "delete from genres where name = ? RETURNING *";
    private static final String DELETE_BY_NAME_SQL2 = "delete from genres where name = ? ";
    private static final String UPDATE_SQL = "update genres set name = ? where id = ?";
    private static final String SELECT_ALL_SQL = "select * from genres ";

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

    private final String defaultName = "Comedy";
    private final int defaultId = 1;

    @BeforeEach
    void initializeBD() {
        jdbcOperations.update(INSERT_SQL, defaultId, defaultName);
    }

    @AfterEach
    void deleteDB() {
        List<Genre> result = jdbcOperations.query(DELETE_BY_NAME_SQL,
                (rs, rowNum) -> Genre.builder()
                        .id(rs.getInt("id"))
                        .name(rs.getString("name"))
                        .build(),
                defaultName);
    }

    @Test
    void testInsert() {

        List<Genre> genres = getAllGenres();

        Assert.assertEquals(1, genres.size());
    }


    @Test
    void testUpdate() {
        jdbcTemplate.update(UPDATE_SQL,
                "defaultNewGenreName", defaultId);
        List<Genre> genres = getAllGenres();

        Assert.assertEquals("defaultNewGenreName", genres.get(0).getName());
    }


    @Test
    void testDelete() {
        List<Genre> genres = getAllGenres();
        for (Genre g : genres)
            jdbcTemplate.update(DELETE_BY_NAME_SQL2, g.getName());
        Assert.assertEquals(0, getAllGenres().size());
    }

    private List<Genre> getAllGenres() {
        return jdbcTemplate.query(
                SELECT_ALL_SQL,
                new GenreRowMapper());
    }

}
