package com.nazarov.movieland.service;

import com.nazarov.movieland.currency_converter.CurrencyConverter;
import com.nazarov.movieland.entity.Movie;
import com.nazarov.movieland.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MovieService {

    public static final int QUANTITY_OF_RANDOM_MOVIES = 3;
    public static final String ORDER_DESC = "desc";
    private final MovieRepository movieRepository;
    private final CurrencyConverter currencyConverter;

    public List<Movie> findAll() {
        log.info("MOVIE SERVICE: FIND ALL");
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
        double uah = toConvert.getPrice();
        double result = currencyConverter.convertNativeToForeign(uah, currency);
        toConvert.setPrice(result);
        return toConvert;
    }

    public boolean orderCheck(String order) {
        return (order.toLowerCase().equals(ORDER_DESC)) ? true : false;
    }

    public List<Movie> findByTitleContaining(String title) {
        title.toUpperCase();
        return movieRepository.findByTitleContaining(title);
    }

    public void markToDelete(Long id) {
        movieRepository.markForDelete(id);
        log.info("MOVIE SERVICE: mark movie with id {} to delete", id);
    }

    public void unMarkToDelete(Long id) {
        movieRepository.unMarkForDelete(id);
        log.info("MOVIE SERVICE: unmark movie with id {} to delete", id);
    }

    public void findAndDeleteMarkedItems() {
        deleteMarkedMovies(findMoviesWithDeleteMark());
        log.info("MOVIE SERVICE: marked movies has been deleted");
    }

    List<Movie> findMoviesWithDeleteMark() {
        return movieRepository.findMoviesWithDeleteMark();
    }

    void deleteMarkedMovies(List<Movie> markedMovies) {
        Long id;
        for (int i = 0; i < markedMovies.size(); i++) {
            id = markedMovies.get(i).getId();
            movieRepository.deleteById(id);
        }
    }

    public void editDescription(String description, Long id) {
        movieRepository.updateMovieDescriptionById(description, id);
    }
}

