package ru.sberbank.pao.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sberbank.pao.dao.api.MovieDAO;
import ru.sberbank.pao.entities.Movie;

import java.util.List;

@Service
public class MovieService {

    private final MovieDAO movieDAO;

    @Autowired
    public MovieService(MovieDAO movieDAO) {
        this.movieDAO = movieDAO;
    }


    public List<Movie> getMovies() {
        return movieDAO.findAll();
    }


    public Movie createMovie(Movie movie) {

        return movieDAO.save(movie);
    }

    public List<Movie> saveAll(List<Movie> movies) {
        return movieDAO.saveAll(movies);
    }

    public void cleanDB() {
        movieDAO.deleteAll();
    }
}
