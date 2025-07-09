package com.learn.bookstore.services;

import com.learn.bookstore.models.Review;

import java.util.List;

public interface ReviewService {

    Review addReview(Long userId, Long bookId, Review review);

    List<Review> getReviewsByBook(Long bookId);

    List<Review> getReviewsByUser(Long userId);

}
