package ru.sberbank.pao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class Boot {

    public static void main(String[] args) {
        SpringApplication.run(Boot.class, args);
    }

}

