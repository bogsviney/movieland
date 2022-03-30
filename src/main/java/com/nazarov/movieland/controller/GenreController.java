package com.nazarov.movieland.controller;

import com.nazarov.movieland.entity.Genre;
import com.nazarov.movieland.service.GenreService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/api/v1/genre")
public class GenreController {

    public final GenreService genreService;

    @GetMapping
    public List<Genre> findAll() {
        log.info("GENRE CONTROLLER: get all genres");
        return genreService.findAll();
    }

    @PostMapping("add")
    public Genre addGenre(Genre genre) {
        log.info("GENRE CONTROLLER: {} genre has been added", genre.getName());
        return genreService.addGenre(genre);
    }
}
