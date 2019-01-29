package ru.sberbank.pao.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.sberbank.pao.transfrormers.MovieTransformer;

@Component
public class FillDataBaseAfterStartUpRunner implements CommandLineRunner {

    private final MovieService movieService;
    private final KinopoiskScraper kinopoiskScraper;
    private final MovieTransformer movieTransformer;

    @Autowired
    public FillDataBaseAfterStartUpRunner(MovieService movieService,
                                          KinopoiskScraper kinopoiskScraper,
                                          MovieTransformer movieTransformer) {
        this.movieService = movieService;
        this.kinopoiskScraper = kinopoiskScraper;
        this.movieTransformer = movieTransformer;
    }

    @Override
    public void run(String... args) {
        movieService.saveAll(movieTransformer.fromListDTO(kinopoiskScraper.getTop()));
    }
}
