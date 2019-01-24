package ru.sberbank.pao.services;

import ru.sberbank.pao.dao.api.MovieDAO;
import ru.sberbank.pao.entities.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
