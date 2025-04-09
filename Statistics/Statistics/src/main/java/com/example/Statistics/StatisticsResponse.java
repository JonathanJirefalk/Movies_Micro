package com.example.Statistics;

public class StatisticsResponse {

    private Statistics statistics;
    private Movie movie;

    public StatisticsResponse(Statistics statistics, Movie movie) {
        this.statistics = statistics;
        this.movie = movie;
    }

    public Statistics getStatistics() {
        return statistics;
    }

    public void setStatistics(Statistics statistics) {
        this.statistics = statistics;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}