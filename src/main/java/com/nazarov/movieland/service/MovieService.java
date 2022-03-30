package com.nazarov.movieland.service;

import com.nazarov.movieland.entity.Movie;
import com.nazarov.movieland.repository.MovieRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;

    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    public List<Movie> findThreeRandomMovies() {
        List<Movie> allMovies = findAll();
        List<Movie> randomMovies = new ArrayList<>();
        Collections.shuffle(allMovies);

        for (int i = 0; i < 3; i++) {
            randomMovies.add(allMovies.get(i));
        }
        return randomMovies;
    }

    public Movie addMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    public List<Movie> sortByRating(String order) {
        if (order.equals("asc")) {
            return movieRepository.sortByRatingAsc();
        } else if (order.equals("desc")) {
            return movieRepository.sortByRatingDesc();
        } else {
            return null;
        }
    }
}
