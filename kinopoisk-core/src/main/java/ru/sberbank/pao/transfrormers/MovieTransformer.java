package ru.sberbank.pao.transfrormers;

import org.springframework.stereotype.Component;
import ru.sberbank.pao.dto.MovieDTO;
import ru.sberbank.pao.entities.Movie;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MovieTransformer {

    public List<Movie> fromListDTO(List<MovieDTO> movieDTOS) {
        return movieDTOS.stream().map(this::fromDTO).collect(Collectors.toList());
    }

    public Movie fromDTO(MovieDTO movieDTO) {
        final String trimmed = movieDTO.getRate().replace(" ", "");
        final Double rate = Double.valueOf(trimmed);
        return new Movie(movieDTO.getPosition(),
                movieDTO.getName(),
                movieDTO.getOriginalName(),
                rate,
                movieDTO.getReleaseYear(),
                movieDTO.getVotes());
    }


}
