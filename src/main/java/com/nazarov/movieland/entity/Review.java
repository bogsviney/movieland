package com.nazarov.movieland.entity;

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
    private String reviewContent;
    @ManyToOne(cascade = CascadeType.REFRESH, optional = false)
    @JoinColumn(name = "movie_id")
    private Movie movie;
}
