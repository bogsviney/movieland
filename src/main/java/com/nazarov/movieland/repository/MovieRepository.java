package com.nazarov.movieland.repository;

import com.nazarov.movieland.entity.Movie;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query("select u from Movie u order by rating asc")
    List<Movie> sortByRatingAsc();

    @Query("select u from Movie u order by rating desc")
    List<Movie> sortByRatingDesc();

    @Query("select u from Movie u order by price asc")
    List<Movie> sortByPriceAsc();

    @Query("select u from Movie u order by price desc")
    List<Movie> sortByPriceDesc();

    @Query("select u from Movie u where id = ?1")
    Movie getById(Long id);

    @Query("select u from Movie u where UPPER (title) like concat ('%',UPPER (:title),'%')")
    List<Movie> findByTitleContaining(@Param("title") String title);

    @Modifying
    @Transactional
    @Query(
            value = "update movies set delete_mark = true where id = ?1",
            nativeQuery = true
    )
    int markForDelete(Long id);

    @Modifying
    @Transactional
    @Query(
            value = "update movies set delete_mark = false where id = ?1",
            nativeQuery = true
    )
    int unMarkForDelete(Long id);

    @Query("select u from Movie u where delete_mark = true")
    List<Movie> findMoviesWithDeleteMark();

    @Modifying
    @Transactional
    @Query(
            value = "update movies set description = ?1 where id = ?2",
            nativeQuery = true
    )
    int updateMovieDescriptionById(String description, Long id);
}


