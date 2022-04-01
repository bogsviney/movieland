package com.nazarov.movieland.controller;

import com.nazarov.movieland.entity.Genre;
import com.nazarov.movieland.entity.Movie;
import com.nazarov.movieland.service.GenreService;
import com.nazarov.movieland.service.MovieService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/api/v1/movie")
public class MovieController {

    public final MovieService movieService;
    public final GenreService genreService;

    @GetMapping
    public List<Movie> findAll() {
        log.info("MOVIE CONTROLLER: get all movies");
        return movieService.findAll();
    }

    @GetMapping("random")
    public List<Movie> findThreeRandomMovies() {
        log.info("MOVIE CONTROLLER: get three random movies");
        return movieService.findThreeRandomMovies();
    }

    @PostMapping("add")
    public Movie addMovie(Movie movie) {
        log.info("MOVIE CONTROLLER: ---{}--- movie has been added", movie.getTitle());
        return movieService.addMovie(movie);
    }

    @GetMapping("genre/{genreId}")
    public List<Movie> getMoviesByGenreId(@PathVariable Long genreId) {
        Genre targetGenre = genreService.getById(genreId);
        log.info("MOVIE CONTROLLER: get all movies by {} genre", genreService.getById(genreId).getName());
        return targetGenre.getMovies();
    }

    @GetMapping(params = {"rating"})
    public List<Movie> sortByRating(String rating) {
        log.info("MOVIE CONTROLLER: sort by rating in {} order", rating);
        return movieService.sortByRating(rating);
    }

    @GetMapping(params = {"price"})
    public List<Movie> sortByPrice(String price) {
        log.info("MOVIE CONTROLLER: sort by price in {} order", price);
        return movieService.sortByPrice(price);
    }

    @GetMapping("{id}")
    public Movie getById(@PathVariable Long id){
        log.info("MOVIE CONTROLLER: find movie with id {}", id);
        return movieService.getById(id);
    }

    @GetMapping("{id}/{currency}")
    public Movie getByIdWithCurrencyConvertation(@PathVariable Long id, @PathVariable String currency){
        log.info("MOVIE CONTROLLER: find movie with id {} price in {}", id, currency);
        return movieService.getByIdWithCurrencyConvertation(id,currency);
    }
}
