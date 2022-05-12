package com.example.beerreview.service;

import com.example.beerreview.entity.Rating;
import com.example.beerreview.exception.ExistsException;
import com.example.beerreview.repository.PostRepository;
import com.example.beerreview.repository.RatingRepository;
import com.example.beerreview.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RatingService {
    @Autowired
    private RatingRepository ratingRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;

    public RatingService(RatingRepository ratingRepository, UserRepository userRepository, PostRepository postRepository) {
        this.ratingRepository = ratingRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    public RatingService() {
    }


    public Rating addRating(float rating, long postId, long userId) {
        if (ratingRepository.findByRatingAndPostIdAndUserId(rating, postId, userId).isPresent()) {
            throw new ExistsException();
        }

        return ratingRepository.save(new Rating(0L, rating, userRepository.findById(userId).get(), postRepository.getById(postId)));
    }

    public void removeRating(float rating, long postId, long userId) {
        ratingRepository.findByRatingAndPostIdAndUserId(rating, postId, userId).ifPresent(ratingRepository::delete);
    }
}

