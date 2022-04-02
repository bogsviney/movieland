package com.nazarov.movieland.service;

import com.nazarov.movieland.entity.Movie;
import com.nazarov.movieland.entity.Review;
import com.nazarov.movieland.repository.MovieRepository;
import com.nazarov.movieland.repository.ReviewRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MovieRepository movieRepository;

    public List<Review> findAll() {
        return reviewRepository.findAll();
    }

    public Review addReview(Review review, Long movieId) {
        Movie movie = movieRepository.getById(movieId);
        review.setMovie(movie);
        reviewRepository.save(review);
        log.info("REVIEW SERVICE: new review to --> {} <-- movie added", movie.getTitle());
        return review;
    }

    public void delete(Long id){
        reviewRepository.deleteById(id);
        log.info("REVIEW SERVICE: review with id: {} has been deleted", id);
    }
}
