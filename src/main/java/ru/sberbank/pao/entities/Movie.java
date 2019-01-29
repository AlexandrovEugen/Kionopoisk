package ru.sberbank.pao.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Table(
        uniqueConstraints = @UniqueConstraint(columnNames = {"originalRateOrder", "name", "originalName"})
)
@Entity
@Getter
@ToString
@EqualsAndHashCode
public class Movie implements Serializable {

    private static final long serialVersionUID = 7156526077883281623L;


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private Integer originalRateOrder;

    private String name;

    private String originalName;

    private Double rate;

    private LocalDate releaseDate;

    private String amountOfVotes;

    public Movie() {

    }

    public Movie(Integer originalRateOrder, String name, String originalName, Double rate, LocalDate releaseDate, String amountOfVotes) {
        this.originalRateOrder = originalRateOrder;
        this.name = name;
        this.originalName = originalName;
        this.rate = rate;
        this.releaseDate = releaseDate;
        this.amountOfVotes = amountOfVotes;
    }

}
