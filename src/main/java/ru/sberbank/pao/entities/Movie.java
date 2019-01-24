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

    private String title;

    private Long rate;

    public Movie() {

    }

    public Movie(Long id, Integer originalRateOrder, String title, Long rate) {
        this.id = id;
        this.originalRateOrder = originalRateOrder;
        this.title = title;
        this.rate = rate;
    }
}
