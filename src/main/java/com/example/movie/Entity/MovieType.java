package com.example.movie.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "movie-type")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovieType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "movie-type-name")
    private String movieTypeName;

    @Column(name = "is-active")
    private Boolean isActive;

    @OneToMany(mappedBy = "movieType")
    @JsonIgnore
    private List<Movie> movieList;
}
