package com.example.User;

public class Review {

    private Long id;
    private String text;
    private Long userId;
    private int rating;

    public Review(Long id, String text, Long userId, int rating) {

        this.id = id;
        this.text = text;
        this.userId = userId;
        this.rating = rating;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}