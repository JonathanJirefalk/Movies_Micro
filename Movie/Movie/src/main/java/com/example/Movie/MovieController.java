package com.example.Movie;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;
    private final WebClient reviewClient;
    private final WebClient statisticsClient;

    public MovieController(MovieService movieService, WebClient.Builder reviewClientBuilder, WebClient.Builder statisticsClientBuilder) {

        this.movieService = movieService;
        this.reviewClient = reviewClientBuilder.baseUrl("http://localhost:8082").build();
        this.statisticsClient = statisticsClientBuilder.baseUrl("http://localhost:8084").build();
    }




    //Read______________________________________________________________________________________________________________

    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovies() {

        return ResponseEntity.ok(movieService.getAllMovies());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable Long id) {

        Movie movie = movieService.getMovieById(id);
        return ResponseEntity.ok(movie);
    }

    @GetMapping("/{id}/reviews")
    public ResponseEntity<MovieResponse> getMovieReviews(@PathVariable Long id) {

        Movie movie = movieService.getMovieById(id);
        Flux<Review> reviewFlux = getReview(id);

        return ResponseEntity.ok(new MovieResponse(movie, reviewFlux.collectList().block()));
    }

    @GetMapping("/{id}/statistics")
    public Mono<ResponseEntity<MovieResponse>> getMovieStatistics(@PathVariable Long id) {

        Movie movie = movieService.getMovieById(id);
        Mono<Statistics> statisticsMono = getStatistics(id);

        return statisticsMono.map(statisticsResponse -> ResponseEntity.ok(new MovieResponse(movie, statisticsResponse)));
    }

    @GetMapping("/{id}/combined")
    public Mono<ResponseEntity<MovieResponse>> getMovieCombined(@PathVariable Long id) {

        Movie movie = movieService.getMovieById(id);
        Mono<Statistics> statisticsMono = getStatistics(id);
        Mono<List<Review>> reviewMono = getReview(id).collectList();

        return Mono.zip(statisticsMono, reviewMono).map(tuple ->
        {
            Statistics statistics = tuple.getT1();
            List<Review> review = tuple.getT2();
            MovieResponse result = new MovieResponse(movie, statistics, review);

            return ResponseEntity.ok(result);
        });
    }



    //Create____________________________________________________________________________________________________________

    @PostMapping
    public ResponseEntity<Movie> createMovie(@RequestBody Movie movie) {

        return ResponseEntity.ok(movieService.newMovie(movie));
    }




    //Edit______________________________________________________________________________________________________________

    @PutMapping("/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable Long id, @RequestBody Movie updatedMovie) {

        return ResponseEntity.ok(movieService.updateMovie(id, updatedMovie));
    }




    //Delete____________________________________________________________________________________________________________

    @DeleteMapping("/{id}")
    public void deleteMovie(@PathVariable Long id) {

        movieService.deleteMovie(id);
    }




    //Other Functions___________________________________________________________________________________________________

    private Flux<Review> getReview(Long id){

        return reviewClient.get().uri("/reviews/" + id + "/movie").retrieve().bodyToFlux(Review.class);
    }

    private Mono<Statistics> getStatistics(Long id){

        return statisticsClient.get().uri("/statistics/" + id + "/movie").retrieve().bodyToMono(Statistics.class);
    }
}