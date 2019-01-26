package ru.sberbank.pao.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@ToString
@EqualsAndHashCode
public class Movie {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private Integer originalRateOrder;

    private String name;

    private String originalName;

    private Double rate;

    private String releaseDate;

    private String amountOfVotes;

    public Movie() {

    }

    public Movie(Integer originalRateOrder, String name, String originalName, Double rate, String releaseDate, String amountOfVotes) {
        this.originalRateOrder = originalRateOrder;
        this.name = name;
        this.originalName = originalName;
        this.rate = rate;
        this.releaseDate = releaseDate;
        this.amountOfVotes = amountOfVotes;
    }

}
