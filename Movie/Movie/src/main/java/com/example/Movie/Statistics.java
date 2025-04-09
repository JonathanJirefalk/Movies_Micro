package com.example.Movie;

public class Statistics {

    private Long id;
    private Long movieId;
    private Integer rating;
    private Integer releaseYear;
    private Integer totalGrossing;

    public Statistics(Long id, Long movieId, Integer rating, Integer releaseYear, Integer totalGrossing) {
        this.id = id;
        this.movieId = movieId;
        this.rating = rating;
        this.releaseYear = releaseYear;
        this.totalGrossing = totalGrossing;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Integer getTotalGrossing() {
        return totalGrossing;
    }

    public void setTotalGrossing(Integer totalGrossing) {
        this.totalGrossing = totalGrossing;
    }
}
