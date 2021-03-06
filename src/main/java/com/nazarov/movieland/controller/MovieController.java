package com.nazarov.movieland.controller;

import com.nazarov.movieland.entity.Genre;
import com.nazarov.movieland.entity.Movie;
import com.nazarov.movieland.service.GenreService;
import com.nazarov.movieland.service.MovieService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/api/v1/movie")
public class MovieController {

    public final MovieService movieService;
    public final GenreService genreService;

    @GetMapping
    public Page<Movie> findAllWithSortingAndPagination(
            @RequestParam Optional<Integer> page,
            @RequestParam Optional<String> sortBy
    ) {
        log.info("get movies sorted by {} , page number {} ", sortBy, page);
        return movieService.findAllPaginatedAndSorted(page, sortBy);
    }

    @GetMapping("random")
    public List<Movie> findRandomMovies() {
        log.info("get random movies");
        return movieService.findRandomMovies();
    }

    @PostMapping("add")
    public Movie addMovie(Movie movie) {
        log.info("---{}--- movie has been added", movie.getTitle());
        return movieService.addMovie(movie);
    }

    @PutMapping("{id}")
    public void editMovieDescription(@PathVariable Long id, @RequestBody String description) {
        movieService.editDescription(description, id);
        log.info("--- movie has been updated");
    }

    @GetMapping("genre/{genreId}")
    public List<Movie> getMoviesByGenreId(@PathVariable Long genreId) {
        Genre targetGenre = genreService.getById(genreId);
        log.info("get all movies by {} genre", targetGenre.getName());
        return targetGenre.getMovies();
    }

    @GetMapping(params = {"rating"})
    public List<Movie> sortByRating(String rating) {
        log.info("sort by rating in {} order", rating);
        return movieService.sortByRating(rating);
    }

    @GetMapping(params = {"price"})
    public List<Movie> sortByPrice(String price) {
        log.info("sort by price in {} order", price);
        return movieService.sortByPrice(price);
    }

    @GetMapping("{id}")
    public Movie getById(@PathVariable Long id) {
        log.info("find movie with id {}", id);
        return movieService.getById(id);
    }

    @GetMapping(value = "{id}", params = "currency")
    @ResponseBody
    public Movie getByIdWithCurrencyConvertation(@PathVariable Long id, @RequestParam String currency) {
        log.info("find movie with id {} price in {}", id, currency);
        return movieService.getByIdWithCurrencyConvertation(id, currency);
    }

    @GetMapping(value = "search", params = "title")
    @ResponseBody
    public List<Movie> searchByTitle(@RequestParam String title) {
        log.info("search movies by --> {} <--", title);
        return movieService.findByTitleContaining(title);
    }

    @PostMapping("{id}/unmark")
    public void unMarkToDelete(@PathVariable Long id) {
        movieService.unMarkToDelete(id);
        log.info("unmark movie with id {} to delete", id);
    }

    @DeleteMapping("{id}")
    public void markToDelete(@PathVariable Long id) {
        movieService.markToDelete(id);
        log.info("mark movie with id {} to delete", id);
    }
}
