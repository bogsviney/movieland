package com.nazarov.movieland.service;

import com.nazarov.movieland.currency_converter.CurrencyConverter;
import com.nazarov.movieland.entity.Movie;
import com.nazarov.movieland.repository.MovieRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MovieServiceTest {

    private CurrencyConverter currencyConverter;
    @Mock
    private MovieRepository movieRepository;


    @Test
    void findAllMoviesTest() {
        MovieService movieService = new MovieService(movieRepository, currencyConverter);
        List<Movie> movies = new ArrayList<>();

        Movie first = Movie.builder()
                .id(1L)
                .description("ruzzian warship go fcuk yourself")
                .price(100.1)
                .rating(8.8)
                .title("Brave new world")
                .year(2022)
                .build();
        movies.add(first);

        Movie second = Movie.builder()
                .id(2L)
                .description("1001 hobbit run")
                .price(43.1)
                .rating(10.8)
                .title("Grave Digger")
                .year(2011)
                .build();
        movies.add(second);

        Movie third = Movie.builder()
                .id(3L)
                .description("The Mystical Potato Head Groove Thing")
                .price(56.6)
                .rating(10.8)
                .title("Joe")
                .year(1999)
                .build();
        movies.add(third);

        Mockito.when(movieRepository.findAll()).thenReturn(movies);

        List<Movie> actual = movieService.findAll();
        assertNotNull(actual);
        assertEquals(3, actual.size());
        assertEquals("The Mystical Potato Head Groove Thing", actual.get(2).getDescription());
        assertSame(first, actual.get(0));
    }

    @Test
    void findMovieByIdTest() {
        MovieService movieService = new MovieService(movieRepository, currencyConverter);

        Movie movie = Movie.builder()
                .id(88L)
                .description("Pursuit of Vikings")
                .price(1212.6)
                .rating(20.8)
                .title("Joe")
                .year(2099)
                .build();

        Mockito.when(movieRepository.getById(88L)).thenReturn(movie);

        Movie actual = movieService.getById(88L);

        assertEquals("Pursuit of Vikings", actual.getDescription());
        assertEquals("Joe", actual.getTitle());
    }

    @Test
    void sortByRatingTest() {
        MovieService movieService = new MovieService(movieRepository, currencyConverter);
        List<Movie> movies = new ArrayList<>();

        Movie first = Movie.builder()
                .id(1L)
                .description("ruzzian warship go fcuk yourself")
                .price(100.1)
                .rating(8.8)
                .title("Brave new world")
                .year(2022)
                .build();
        movies.add(first);

        Movie second = Movie.builder()
                .id(2L)
                .description("1001 hobbit run")
                .price(43.1)
                .rating(4.8)
                .title("Grave Digger")
                .year(2011)
                .build();
        movies.add(second);

        Movie third = Movie.builder()
                .id(3L)
                .description("The Mystical Potato Head Groove Thing")
                .price(56.6)
                .rating(5.8)
                .title("Joe")
                .year(1999)
                .build();
        movies.add(third);

        Mockito.when(movieRepository.sortByRatingDesc()).thenReturn(movies);

        List<Movie> actual = movieService.sortByRating("desc");

        assertEquals(first, actual.get(0));
        assertEquals(third, actual.get(2));
        assertEquals(second, actual.get(1));
    }

    @Test
    void sortByPriceTest() {
        MovieService movieService = new MovieService(movieRepository, currencyConverter);
        List<Movie> movies = new ArrayList<>();

        Movie first = Movie.builder()
                .id(1L)
                .description("ruzzian warship go fcuk yourself")
                .price(100.1)
                .rating(8.8)
                .title("Brave new world")
                .year(2022)
                .build();
        movies.add(first);

        Movie second = Movie.builder()
                .id(2L)
                .description("1001 hobbit run")
                .price(200.1)
                .rating(4.8)
                .title("Grave Digger")
                .year(2011)
                .build();
        movies.add(second);

        Movie third = Movie.builder()
                .id(3L)
                .description("The Mystical Potato Head Groove Thing")
                .price(300.6)
                .rating(5.8)
                .title("Joe")
                .year(1999)
                .build();
        movies.add(third);

        Mockito.when(movieRepository.sortByPriceAsc()).thenReturn(movies);

        List<Movie> actual = movieService.sortByPrice("asc");

        assertEquals(first, actual.get(0));
        assertEquals(second, actual.get(1));
        assertEquals(third, actual.get(2));
    }

    @Test
    void getByIdTest() {
        MovieService movieService = new MovieService(movieRepository, currencyConverter);

        Movie third = Movie.builder()
                .id(3L)
                .description("The Mystical Potato Head Groove Thing")
                .price(300.6)
                .rating(5.8)
                .title("Joe")
                .year(1999)
                .build();

        Mockito.when(movieRepository.getById(3L)).thenReturn(third);

        Movie actual = movieService.getById(3L);

        assertEquals("The Mystical Potato Head Groove Thing", actual.getDescription());
        assertEquals(300.6, actual.getPrice());
        assertEquals(1999, actual.getYear());
        assertEquals(5.8, actual.getRating());
    }

    @Test
    void findByTitleContainingTest() {
        MovieService movieService = new MovieService(movieRepository, currencyConverter);
        List<Movie> movies = new ArrayList<>();
        Movie first = Movie.builder()
                .id(1L)
                .description("ruzzian warship go fcuk yourself")
                .price(100.1)
                .rating(8.8)
                .title("Brave Joe world")
                .year(2022)
                .build();
        movies.add(first);

        Mockito.when(movieRepository.findByTitleContaining("jo")).thenReturn(movies);

        List<Movie> actual = movieService.findByTitleContaining("jo");

        assertEquals(1, actual.size());
        assertEquals("Brave Joe world", actual.get(0).getTitle());
        assertSame(first, actual.get(0));
    }

    @Test
    void findMoviesWithDeleteMarkTest() {
        MovieService movieService = new MovieService(movieRepository, currencyConverter);
        List<Movie> movies = new ArrayList<>();

        Movie third = Movie.builder()
                .id(3L)
                .description("The Mystical Potato Head Groove Thing")
                .price(56.6)
                .rating(10.8)
                .title("Joe")
                .year(1999)
                .deleteMark(true)
                .build();
        movies.add(third);

        Mockito.when(movieRepository.findMoviesWithDeleteMark()).thenReturn(movies);

        List<Movie> actual = movieService.findMoviesWithDeleteMark();

        assertEquals(1, actual.size());
        assertEquals("Joe", actual.get(0).getTitle());
        assertTrue(actual.get(0).isDeleteMark());
    }

    @Test
    void orderCheckTest() {
        MovieService movieService = new MovieService(movieRepository, currencyConverter);

        String orderDesc = "desc";
        String orderAsc = "asc";

        boolean checkDesc = movieService.orderCheck(orderDesc);
        boolean checkAsc = movieService.orderCheck(orderAsc);

        assertFalse(checkAsc);
        assertTrue(checkDesc);
    }
}