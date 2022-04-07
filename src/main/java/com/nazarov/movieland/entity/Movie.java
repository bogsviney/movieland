package com.nazarov.movieland.entity;

import lombok.*;

import javax.persistence.*;
import java.util.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(
        name = "movies"
)
public class
Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private int year;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "movies_genres",
            joinColumns = {@JoinColumn(name = "movie_id")},
            inverseJoinColumns = {@JoinColumn(name = "genre_id")})

    private Set<Genre> genres = new HashSet<>();
    @ManyToMany
    @JoinTable(name = "movies_countries",
            joinColumns = {@JoinColumn(name = "movie_id")},
            inverseJoinColumns = {@JoinColumn(name = "country_id")})
    private Set<Country> countries = new HashSet<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "movie", orphanRemoval = true)
    private List<Review> reviews;
    private String description;
    private double rating;
    private double price;
    private boolean deleteMark;
}
