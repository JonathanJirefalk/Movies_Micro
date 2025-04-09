package com.example.User;

import java.util.List;

public class UserResponse {

    private User user;
    private List<Review> reviews;

    public UserResponse(User user, List<Review> reviews) {

        this.user = user;
        this.reviews = reviews;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

}
