package com.nazarov.movieland.service;

import com.nazarov.movieland.entity.Genre;
import com.nazarov.movieland.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreService {

    private List<Genre> cashedGenreList = Collections.synchronizedList(new ArrayList<>());

    @PostConstruct
    void init(){
        fillGenreCache();
    }

    public void fillGenreCache(){
        cashedGenreList = genreRepository.findAll();
    }

    private final GenreRepository genreRepository;

    public List<Genre> findAll() {
        return cashedGenreList;
    }

    public Genre addGenre(Genre genre) {
        return genreRepository.save(genre);
    }

    public Genre getById(Long id) {
        return genreRepository.getById(id);
    }
}
