package ru.sberbank.pao.dao.api;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sberbank.pao.entities.Movie;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MovieDAO extends JpaRepository<Movie, Long> {

    List<Movie> findByReleaseDate(LocalDate date, Pageable numberOfReturnedResults);

}
