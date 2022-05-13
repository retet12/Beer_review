package com.example.beerreview;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BeerReviewApplication {

    public static void main(String[] args) {
        SpringApplication.run(BeerReviewApplication.class, args);
    }

}
