package com.emiro.jobapp.emirojobs.review;

import io.micrometer.observation.ObservationFilter;

import java.util.List;
import java.util.Optional;

public interface ReviewService {
    List<Review> findAll(Long companyId);
    Optional<Review> findById(Long companyId, Long reviewId); ;
    boolean createReview(Long companyId, Review review);
    Optional<Review> updateReview(Long companyId, Long reviewId, Review updatedReview);

    boolean deleteReview(Long companyId, Long reviewId);
}
