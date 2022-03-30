package com.nazarov.movieland.repository;

import com.nazarov.movieland.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre,Long> {
}
