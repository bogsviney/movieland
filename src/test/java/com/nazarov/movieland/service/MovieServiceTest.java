package com.nazarov.movieland.service;

import com.nazarov.movieland.entity.Movie;
import com.nazarov.movieland.repository.MovieRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MovieServiceTest {

    @Mock
    private MovieRepository movieRepository;


    @Test
    void findAllMoviesTest() {
        MovieService movieService = new MovieService(movieRepository);
        List<Movie> movies = new ArrayList<>();

        Movie first = Movie.builder()
                .id(1L)
                .country("Narnia")
                .description("ruzzian warship go fcuk yourself")
                .price(100.1)
                .rating(8.8)
                .title("Brave new world")
                .year(2022)
                .build();
        movies.add(first);

        Movie second = Movie.builder()
                .id(2L)
                .country("Thror")
                .description("1001 hobbit run")
                .price(43.1)
                .rating(10.8)
                .title("Grave Digger")
                .year(2011)
                .build();
        movies.add(second);

        Movie third = Movie.builder()
                .id(3L)
                .country("G3")
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
        MovieService movieService = new MovieService(movieRepository);

        Movie movie = Movie.builder()
                .id(88L)
                .country("Walhalla")
                .description("Pursuit of Vikings")
                .price(1212.6)
                .rating(20.8)
                .title("Joe")
                .year(2099)
                .build();

        Mockito.when(movieRepository.getById(88L)).thenReturn(movie);

        Movie actual = movieService.getById(88L);

        assertEquals("Walhalla", actual.getCountry());
        assertEquals("Pursuit of Vikings", actual.getDescription());
        assertEquals("Joe", actual.getTitle());
    }


}