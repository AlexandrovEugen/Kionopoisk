package ru.sberbank.pao.controllers;

import io.swagger.annotations.ApiOperation;
import ru.sberbank.pao.entities.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sberbank.pao.services.MovieService;

import java.util.List;

@RestController
@RequestMapping("/top")
public class MovieController {


    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
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

}
