package com.nazarov.movieland.controller;

import com.nazarov.movieland.entity.Genre;
import com.nazarov.movieland.service.GenreService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("{id}")
    public Genre getById(@PathVariable Long id) {
        log.info("GENRE CONTROLLER: get genre by id: {}", id);
        return genreService.getById(id);
    }

    @PostMapping("add")
    public Genre addGenre(Genre genre) {
        log.info("GENRE CONTROLLER: {} genre has been added", genre.getName());
        return genreService.addGenre(genre);
    }
}
