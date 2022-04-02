package com.nazarov.movieland.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(
        name = "reviews"
)
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;
    @ManyToOne(cascade = CascadeType.REFRESH, optional = false)
    @JoinColumn(name = "movie_id")
    private Movie movie;
    @JsonBackReference
    public Movie getMovie() {
        return movie;
    }
}
