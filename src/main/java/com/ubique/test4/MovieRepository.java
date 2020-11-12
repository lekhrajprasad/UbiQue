package com.ubique.test4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.RowMapper;

import javax.annotation.PostConstruct;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
@Repository
public class MovieRepository {
    @Autowired
    private JdbcTemplate template;

    @PostConstruct
    public void createTable() {
        template.execute("CREATE TABLE movies (id bigint auto_increment primary key, name VARCHAR(50), year int, rating int)");
    }

    public void createMovie(String name, int year, int rating) {
        final String insertSql = "INSERT INTO movies(name, year, rating) VALUES(?, ?, ?)";
        //Movie movie = new Movie(name, year, rating);
        Object[] params = new Object[] { name, year, rating };
        template.update(insertSql, params);
    }

    public List<Movie> findMoviesByName(String likeName) {
        String query = "SELECT name, year, rating FROM movies WHERE name LIKE ?";
        return template.query(query, new String[] { likeName },new RowMapper<Movie>(){
            @Override
            public Movie mapRow(ResultSet rs, int rownumber) throws SQLException {
                Movie e=new Movie();
                e.setName(rs.getString(1));
                e.setYear(rs.getInt(2));
                e.setRating(rs.getInt(3));
                return e;
            }
        });
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext config = new AnnotationConfigApplicationContext();
        config.register(Config.class);
        config.refresh();
        MovieRepository repository = config.getBean(MovieRepository.class);

        repository.createMovie("Some movie", 1974, 3);
        repository.createMovie("Some other movie", 1993, 2);

        List<Movie> movies = repository.findMoviesByName("Some%");
        for(Movie movie : movies) {
            System.out.println(movie.name + " - " + movie.year + " - " + movie.rating);
        }
    }
}
