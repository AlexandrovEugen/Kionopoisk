package ru.sberbank.pao.services.api;

import ru.sberbank.pao.dto.MovieDTO;

import java.util.List;

public interface Scraper {

    List<MovieDTO> getTop();

}
