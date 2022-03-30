package com.nazarov.movieland.repository;

import com.nazarov.movieland.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query("select u from Movie u order by rating asc")
    List<Movie> sortByRatingAsc();

    @Query("select u from Movie u order by rating desc")
    List<Movie> sortByRatingDesc();
}

