package com.example.Movie;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MovieResponse {

    private Movie movie;
    private Statistics statistics;
    private List<Review> reviews;

    public MovieResponse(Movie movie, List<Review> reviews) {

        this.movie = movie;
        this.reviews = reviews;
    }

    public MovieResponse(Movie movie, Statistics statistics) {
        this.movie = movie;
        this.statistics = statistics;
    }

    public MovieResponse(Movie movie, Statistics statistics, List<Review> reviews) {
        this.movie = movie;
        this.statistics = statistics;
        this.reviews = reviews;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Statistics getStatistics() {
        return statistics;
    }

    public void setStatistics(Statistics statistics) {
        this.statistics = statistics;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
}