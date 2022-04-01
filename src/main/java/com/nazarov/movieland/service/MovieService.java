package com.nazarov.movieland.service;

import com.nazarov.movieland.converter.CurrencyConverter;
import com.nazarov.movieland.entity.Movie;
import com.nazarov.movieland.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;
    private CurrencyConverter currencyConverter = new CurrencyConverter();

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

    public List<Movie> sortByPrice(String order) {
        if (order.equals("asc")) {
            return movieRepository.sortByPriceAsc();
        } else if (order.equals("desc")) {
            return movieRepository.sortByPriceDesc();
        } else {
            return null;
        }
    }

    public Movie getById(Long id) {
        return movieRepository.getById(id);
    }

    public Movie getByIdWithCurrencyConvertation(Long id, String currency) {
        Movie toConvert = movieRepository.getById(id);
        double result = currencyConverter.convert(toConvert.getPrice(), currency);
        toConvert.setPrice(new BigDecimal(result).setScale(2, RoundingMode.HALF_UP).doubleValue());
        return toConvert;
    }
}

