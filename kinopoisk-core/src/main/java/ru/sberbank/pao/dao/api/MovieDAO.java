package ru.sberbank.pao.dao.api;

import ru.sberbank.pao.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieDAO extends JpaRepository<Movie, Long> {
}
