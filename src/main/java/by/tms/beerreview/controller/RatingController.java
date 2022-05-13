package by.tms.beerreview.controller;

import by.tms.beerreview.entity.Rating;
import by.tms.beerreview.exception.NotFoundException;
import by.tms.beerreview.service.RatingService;
import by.tms.beerreview.validator.RatingValidator;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@Api(tags = "Rating",description = "Operation with rating")
@RequestMapping("/api/v1/rating")
public class RatingController {
    private final RatingValidator ratingValidator;
    private final RatingService ratingService;

    public RatingController(RatingValidator ratingValidator, RatingService ratingService) {
        this.ratingValidator = ratingValidator;
        this.ratingService = ratingService;
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "405", description = "Invalid input"),
            @ApiResponse(responseCode = "409", description = "Already exists")
    })
    @ApiOperation(value = "Add Rating", notes = "This can only be done by the logged in user", authorizations = {@Authorization(value = "apiKey")})
    @PostMapping(value = "/{userId}/{postId}", produces = "application/json")
    public ResponseEntity<Rating> save(@ApiParam(value = "The user who added the rating", example = "userId")
                                     @PathVariable("userId") Long userId,
                                       @ApiParam(value = "Add rating for the post", example = "postId")
                                     @PathVariable("postId") Long postId,
                                       @ApiParam(value = "Add rating for the post", example = "rating")
                                     @PathVariable("rating") Float rating){

        ratingValidator.validateID(userId, postId);
        ratingValidator.existsByUserIdAndPostId(userId, postId);

        return ResponseEntity.ok(ratingService.addRating(rating,userId, postId));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "405", description = "Invalid input")
    })
    @ApiOperation(value = "Delete Rating", notes = "This can only be done by the logged in user", authorizations = {@Authorization(value = "apiKey")})
    @DeleteMapping("/{userId}/{postId}")
    public void delete(@ApiParam(value = "The user who deleted the rating", example = "userId")
                       @PathVariable("userId") Long userId,
                       @ApiParam(value = "Remove the rating from the post", example = "postId")
                       @PathVariable("postId") Long postId,@ApiParam(value = "Add rating for the post", example = "rating")
                           @PathVariable("rating") Float rating) {
        ratingValidator.validateID(userId, postId);

        if (ratingValidator.existsByUserIdAndPostId(userId, postId)) {
            ratingService.removeRating(rating, userId, postId);
        } else {
            throw new NotFoundException();
        }
    }
}
