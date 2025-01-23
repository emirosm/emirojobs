package com.emiro.jobapp.emirojobs.review.impl;

import com.emiro.jobapp.emirojobs.company.Company;
import com.emiro.jobapp.emirojobs.company.CompanyService;
import com.emiro.jobapp.emirojobs.review.Review;
import com.emiro.jobapp.emirojobs.review.ReviewRepository;
import com.emiro.jobapp.emirojobs.review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {
    private ReviewRepository reviewRepository;
    private CompanyService companyService;

    public ReviewServiceImpl(ReviewRepository reviewRepository, CompanyService companyService) {
        this.reviewRepository = reviewRepository;
        this.companyService = companyService;
    }

    @Override
    public List<Review> findAll(Long companyId) {
        return reviewRepository.findByCompanyId(companyId);
    }

    @Override
    public Optional<Review> findById(Long companyId, Long reviewId) {
        Optional<Company> company = companyService.getCompanyById(companyId);
        if (company.isPresent()) {
            List<Review> reviewList = company.get().getReviews();
            return Optional.ofNullable(reviewList.stream().filter(review -> Objects.equals(review.getId(), reviewId)).findFirst().orElse(null));
        }
        return Optional.empty();
    }

    @Override
    public boolean createReview(Long companyId, Review review) {
        Optional<Company> company = companyService.getCompanyById(companyId);
        if (company.isPresent()) {
            review.setCompany(company.get());
            reviewRepository.save(review);
            return true;
        }
        return false;
    }

    public Optional<Review> updateReview(Long companyId, Long reviewId, Review updatedReview) {
        return companyService.getCompanyById(companyId)
                .flatMap(company -> findById(companyId, reviewId)
                        .map(existingReview -> {
                            existingReview.setTitle(updatedReview.getTitle());
                            existingReview.setDescription(updatedReview.getDescription());
                            existingReview.setRating(updatedReview.getRating());
                            reviewRepository.save(existingReview); // Save the updated existing review
                            return existingReview;
                        })
                );
    }


    @Override
    public boolean deleteReview(Long companyId, Long reviewId) {
        return companyService.getCompanyById(companyId)
                .flatMap(company -> company.getReviews().stream()
                        .filter(review -> Objects.equals(review.getId(), reviewId))
                        .findFirst())
                .map(reviewToDelete -> {
                    reviewRepository.delete(reviewToDelete);
                    return true;
                })
                .orElse(false);
    }


}
