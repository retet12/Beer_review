package com.example.beerreview.repository;

import com.example.beerreview.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Component
@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
    Optional<Rating> findByRatingAndPostIdAndUserId(Float rating, Long postId, Long userId);
    @Query("select Rating From Rating where rating =:rating")
    Optional<Rating> findRatingAll(Float rating);
}
