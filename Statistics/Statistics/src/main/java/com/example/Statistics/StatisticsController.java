package com.example.Statistics;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/statistics")
public class StatisticsController {

    private final StatisticsService statisticsService;
    private final WebClient movieClient;

    public StatisticsController(StatisticsService statisticsService, WebClient.Builder movieClientBuilder) {
        this.statisticsService = statisticsService;
        this.movieClient = movieClientBuilder.baseUrl("http://localhost:8081").build();
    }




    //Read______________________________________________________________________________________________________________

    @GetMapping
    public ResponseEntity<List<Statistics>> getAllStatistics() {

        return ResponseEntity.ok(statisticsService.getAllStatistics());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Statistics> getStatistics(@PathVariable Long id) {

        Statistics statistics = statisticsService.getStatisticsById(id);

        return ResponseEntity.ok(statistics);
    }

    @GetMapping("/{id}/movie")
    public ResponseEntity<Statistics> getStatisticsByMovieId(@PathVariable Long id) {

        return ResponseEntity.ok(statisticsService.getStatisticsByMovieId(id));
    }

    @GetMapping("/{id}/combined")
    public Mono<ResponseEntity<StatisticsResponse>> getStatisticsAndMovies(@PathVariable Long id) {

        Statistics statistics = statisticsService.getStatisticsById(id);
        Mono<Movie> movieMono = getMovie(statistics.getMovieId());

        return movieMono.map(movieResponse -> ResponseEntity.ok(new StatisticsResponse(statistics, movieResponse)));
    }




    //Create____________________________________________________________________________________________________________

    @PostMapping
    public ResponseEntity<Statistics> addStatistics(@RequestBody Statistics statistics) {

        return ResponseEntity.ok(statisticsService.addStatistics(statistics));
    }




    //Edit______________________________________________________________________________________________________________

    @PutMapping("/{id}")
    public ResponseEntity<Statistics> updateStatistics(@PathVariable Long id, @RequestBody Statistics newStatistics) {

        return ResponseEntity.ok(statisticsService.updateStatistics(id, newStatistics));
    }




    //Delete____________________________________________________________________________________________________________

    @DeleteMapping("/{id}")
    public void deleteStatistics(@PathVariable Long id) {

        statisticsService.deleteStatistics(id);
    }




    //Other Functions___________________________________________________________________________________________________

    private Mono<Movie> getMovie(Long id){

        return movieClient.get().uri("/movies/" + id).retrieve().bodyToMono(Movie.class);
    }
}