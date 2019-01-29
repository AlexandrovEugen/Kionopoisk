package ru.sberbank.pao.transfrormers;

import org.springframework.stereotype.Component;
import ru.sberbank.pao.dto.MovieDTO;
import ru.sberbank.pao.entities.Movie;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MovieTransformer {

    public List<Movie> fromListDTO(List<MovieDTO> movieDTOS) {
        return movieDTOS.stream()
                .map(this::fromDTO)
                .collect(Collectors.toList());
    }

    public Movie fromDTO(MovieDTO movieDTO) {
        final String trimmed = movieDTO.getRate().replace(" ", "");
        final Double rate = Double.valueOf(trimmed);
        return new Movie(movieDTO.getPosition(),
                movieDTO.getName(),
                movieDTO.getOriginalName(),
                rate,
                parseDate(movieDTO.getReleaseYear()),
                movieDTO.getVotes());
    }

    public List<MovieDTO> toListDTO(List<Movie> movies) {
        return movies.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }


    public MovieDTO toDTO(Movie movie) {
        return new MovieDTO(movie.getOriginalRateOrder(),
                movie.getName(),
                movie.getOriginalName(),
                movie.getRate().toString(),
                movie.getAmountOfVotes(),
                String.valueOf(movie.getReleaseDate().getYear()));
    }


    private LocalDate parseDate(String releaseYear) {
        final DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendPattern("yyyy")
                .parseDefaulting(ChronoField.MONTH_OF_YEAR, 1)
                .parseDefaulting(ChronoField.DAY_OF_MONTH, 1)
                .toFormatter();
        return LocalDate.parse(releaseYear, formatter);
    }
}
