package com.example.Review;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public Review newReview(Review review) {
        return reviewRepository.save(review);
    }

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    public Review getReviewById(Long id) {
        return reviewRepository.findById(id).orElse(null);
    }

    public List<Review> getReviewByMovieId(Long movieId) {
        return reviewRepository.findByMovieId(movieId);
    }

    public List<Review> getReviewByUserId(Long userId) {
        return reviewRepository.findByUserId(userId);
    }

    public void deleteReview(Long id){
        reviewRepository.deleteById(id);
    }

    public Review updateReview(Long id, Review updatedReview) {

        Review targetReview = getReviewById(id);

        if(targetReview != null){

            targetReview = updatedReview;
            return newReview(targetReview);
        }else{

            throw new EntityNotFoundException("Review not found");
        }
    }
}