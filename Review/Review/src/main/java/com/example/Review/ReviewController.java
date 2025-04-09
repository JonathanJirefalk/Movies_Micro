package com.example.Review;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;
    private final WebClient movieClient;
    private final WebClient userClient;

    public ReviewController(ReviewService reviewService, WebClient.Builder movieClient, WebClient.Builder userClient) {

        this.reviewService = reviewService;
        this.movieClient = movieClient.baseUrl("http://localhost:8081").build();
        this.userClient = userClient.baseUrl("http://localhost:8083").build();
    }




    //Read______________________________________________________________________________________________________________

    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews(){

        return ResponseEntity.ok(reviewService.getAllReviews());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long id){

        return ResponseEntity.ok(reviewService.getReviewById(id));
    }


    @GetMapping("/{id}/user")
    public List<Review> getReviewByUserId(@PathVariable Long id) {

        return reviewService.getReviewByUserId(id);
    }

    @GetMapping("/{id}/movie")
    public List<Review> getReviewByMovieId(@PathVariable Long id) {

        return reviewService.getReviewByMovieId(id);
    }

    @GetMapping("{id}/combined")
    public Mono<ResponseEntity<ReviewResponse>> getCombinedResult(@PathVariable Long id) {

        Review review = reviewService.getReviewById(id);
        Mono<User> userMono = getUser(review.getUserId());
        Mono<Movie> movieMono = getMovie(review.getMovieId());

        return Mono.zip(userMono, movieMono).map(tuple ->
        {
            User user = tuple.getT1();
            Movie movie = tuple.getT2();
            ReviewResponse result = new ReviewResponse(review, user, movie);

            return ResponseEntity.ok(result);
        });
    }




    //Create____________________________________________________________________________________________________________

    @PostMapping
    public ResponseEntity<Review> createReview(@RequestBody Review review) {

        return ResponseEntity.ok(reviewService.newReview(review));
    }




    //Edit______________________________________________________________________________________________________________

    @PutMapping("/{id}")
    public ResponseEntity<Review> updateReview(@PathVariable Long id, @RequestBody Review newReview) {

        return ResponseEntity.ok(reviewService.updateReview(id, newReview));
    }




    //Delete____________________________________________________________________________________________________________

    @DeleteMapping("/{id}")
    public void deleteReview(@PathVariable Long id) {

        reviewService.deleteReview(id);
    }




    //Other Functions___________________________________________________________________________________________________

    private Mono<User> getUser(Long id){

        return userClient.get().uri("/users/" + id).retrieve().bodyToMono(User.class);
    }

    private Mono<Movie> getMovie(Long id){

        return movieClient.get().uri("/movies/" + id).retrieve().bodyToMono(Movie.class);
    }
}