package com.nazarov.movieland.service;

import com.nazarov.movieland.entity.Movie;
import com.nazarov.movieland.repository.MovieRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;

    public List<Movie> findAll(){
        return movieRepository.findAll();
    }

}
