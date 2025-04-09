package com.example.Review;

public class ReviewResponse {

    private Review review;
    private User user;
    private Movie movie;

    public ReviewResponse(Review review, User user, Movie movie) {

        this.review = review;
        this.user = user;
        this.movie = movie;
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}