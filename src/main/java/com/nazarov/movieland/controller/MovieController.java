package com.nazarov.movieland.controller;

import com.nazarov.movieland.entity.Movie;
import com.nazarov.movieland.service.MovieService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/api/v1/movie")
public class MovieController {

    public final MovieService service;

    @GetMapping
    public List<Movie> findAll(){
        log.info("MOVIE CONTROLLER: get all movies");
        return service.findAll();
    }
}
