package com.example.movie.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "movie")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "movie-duration")
    private Integer movieDuration;

    @Column(name = "end-time")
    private LocalDate endTime;

    @Column(name = "premiere-date")
    private LocalDate premiereDate;

    @Column(name = "description")
    private String description;

    @Column(name = "director")
    private String director;

    @Column(name = "image")
    private String image;

    @Column(name = "hero-image")
    private String heroImage;

    @Column(name = "language")
    private String language;

    @Column(name = "movie-type-id")
    private Integer movieTypeId;

    @Column(name = "name")
    private String name;

    @Column(name = "rate-id")
    private Integer rateId;

    @Column(name = "trailer")
    private String trailer;

    @Column(name = "is-active")
    private Boolean isActive;

    @ManyToOne
    @JoinColumn(name = "id_movietype")
    @JsonIgnore
    private MovieType movieType;

    @ManyToOne
    @JoinColumn(name = "id_rate")
    @JsonIgnore
    private Rate rate;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Schedule> scheduleList;
}
