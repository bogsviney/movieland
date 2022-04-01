package com.nazarov.movieland.service;

import com.nazarov.movieland.converter.CurrencyConverter;
import com.nazarov.movieland.entity.Movie;
import com.nazarov.movieland.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {

    public static final int QUANTITY_OF_RANDOM_MOVIES = 3;
    public static final String ORDER_DESC = "desc";

    private final MovieRepository movieRepository;
    private CurrencyConverter currencyConverter = new CurrencyConverter();

    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    public List<Movie> findRandomMovies() {
        List<Movie> allMoviesRandomOrder = movieRepository.findAllInRandomOrder();
        List<Movie> result = allMoviesRandomOrder.subList(0, QUANTITY_OF_RANDOM_MOVIES);
        return result;
    }

    public Movie addMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    public List<Movie> sortByRating(String order) {
        return orderCheck(order) ? movieRepository.sortByRatingDesc() : movieRepository.sortByRatingAsc();
    }

    public List<Movie> sortByPrice(String order) {
        return orderCheck(order) ? movieRepository.sortByPriceDesc() : movieRepository.sortByPriceAsc();
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

    public boolean orderCheck(String order) {
        return (order.toLowerCase().equals(ORDER_DESC)) ? true : false;
    }
}

