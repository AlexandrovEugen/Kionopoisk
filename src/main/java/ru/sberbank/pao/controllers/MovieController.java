package ru.sberbank.pao.controllers;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sberbank.pao.dto.MovieDTO;
import ru.sberbank.pao.entities.Movie;
import ru.sberbank.pao.services.MovieService;
import ru.sberbank.pao.transfrormers.MovieTransformer;

import java.util.List;

@RestController
@RequestMapping("/kinopoisk")
public class MovieController {


    private final MovieService movieService;
    private final MovieTransformer movieTransformer;

    @Autowired
    public MovieController(MovieService movieService, MovieTransformer movieTransformer) {
        this.movieService = movieService;
        this.movieTransformer = movieTransformer;
    }

    @ApiOperation(value = "Save movie from site")
    @PutMapping("/createMovie")
    public ResponseEntity<Movie> createMovie(@RequestBody Movie movie) {
        return new ResponseEntity<>(movieService.createMovie(movie), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Get list of movies from top")
    @GetMapping("/top")
    public ResponseEntity<List<MovieDTO>> getAll() {
        return new ResponseEntity<>(movieTransformer.toListDTO(movieService.getMovies()), HttpStatus.OK);
    }


    @ApiOperation(value = "Get top of movies by specific year")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "top", value = "exact amount of movies that should be listed on page",
                    required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "year", value = "year of all movies that we want to list",
                    required = true, dataType = "string")
    })
    @GetMapping("/top/{top}/of{year}")
    public ResponseEntity<List<MovieDTO>> getTopMoviesOf(@PathVariable("top") int top, @PathVariable("year") String year) {
        return new ResponseEntity<>(movieTransformer.toListDTO(movieService.getTopMoviesOf(top, year)), HttpStatus.OK);
    }


    @ApiOperation(value = "Clean db")
    @DeleteMapping("/clean")
    public ResponseEntity<Void> cleanDB() {
        movieService.cleanDB();
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
