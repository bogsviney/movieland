package com.nazarov.movieland.repository;

import com.nazarov.movieland.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<Genre,Long> {

    @Query("select u from Genre u where id = ?1")
    Genre getById(Long id);
}
