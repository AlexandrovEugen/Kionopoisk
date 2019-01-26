package ru.sberbank.pao.controllers;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sberbank.pao.entities.Movie;
import ru.sberbank.pao.services.MovieService;
import ru.sberbank.pao.services.impl.KinopoiskScraper;
import ru.sberbank.pao.transfrormers.MovieTransformer;

import java.util.List;

@RestController
@RequestMapping("/top")
public class MovieController {


    private final MovieService movieService;
    private final KinopoiskScraper scraper;
    private final MovieTransformer movieTransformer;

    @Autowired
    public MovieController(MovieService movieService, KinopoiskScraper scraper, MovieTransformer movieTransformer) {
        this.movieService = movieService;
        this.scraper = scraper;
        this.movieTransformer = movieTransformer;
    }

    @ApiOperation(value = "Save movie from site")
    @PutMapping
    public ResponseEntity<Movie> createMovie(@RequestBody Movie movie) {
        return new ResponseEntity<>(movieService.createMovie(movie), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Get list of movies from top")
    @GetMapping
    public ResponseEntity<List<Movie>> getAll() {
        return new ResponseEntity<>(movieService.getMovies(), HttpStatus.OK);
    }

    @ApiOperation(value = "Fill db with data from Kinopoisk")
    @PostMapping
    public ResponseEntity<List<Movie>> fillData() {
        return new ResponseEntity<>(movieService.saveAll(movieTransformer.fromListDTO(scraper.getTop())), HttpStatus.OK);
    }

    @ApiOperation(value = "Clean db")
    @DeleteMapping
    public ResponseEntity<Void> cleanDB() {
        movieService.cleanDB();
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
