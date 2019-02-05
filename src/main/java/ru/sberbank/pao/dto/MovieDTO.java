package ru.sberbank.pao.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@ToString
@EqualsAndHashCode
public class MovieDTO implements Serializable {

    private static final long serialVersionUID = 7156526077883281623L;

    private final Integer position;
    private final String name;
    private final String originalName;
    private final String rate;
    private final String votes;
    private String releaseYear;


    public MovieDTO(Integer position, String name, String originalName, String rate, String votes, String releaseYear) {
        this.position = position;
        this.name = name;
        this.originalName = originalName;
        this.rate = rate;
        this.votes = votes;
        this.releaseYear = releaseYear;
    }
}
