package ru.sberbank.pao.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sberbank.pao.dao.api.MovieDAO;
import ru.sberbank.pao.entities.Movie;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.List;

@Transactional
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

    public List<Movie> getTopMoviesOf(int top, String year) {
        final Pageable request = PageRequest.of(0, top, Sort.Direction.ASC, "originalRateOrder");
        final DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendPattern("yyyy")
                .parseDefaulting(ChronoField.MONTH_OF_YEAR, 1)
                .parseDefaulting(ChronoField.DAY_OF_MONTH, 1)
                .toFormatter();
        return movieDAO.findByReleaseDate(LocalDate.parse(year, formatter),request);
    }
}
