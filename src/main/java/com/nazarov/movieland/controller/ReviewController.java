package com.nazarov.movieland.controller;

import com.nazarov.movieland.entity.Review;
import com.nazarov.movieland.service.ReviewService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/api/v1")
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("review")
    public List<Review> findAll() {
        log.info("REVIEW CONTROLLER: get all reviews");
        return reviewService.findAll();
    }

    @PostMapping("movie/{movieId}/review")
    public Review addReview(@RequestBody Review review, @PathVariable Long movieId) {
        Review toSave = reviewService.addReview(review, movieId);
        log.info("REVIEW CONTROLLER: new review added");
        return toSave;
    }
}
