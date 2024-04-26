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
@Table(name = "schedule")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "price")
    private Double price;

    @Column(name = "start-at")
    private LocalDate startAt;

    @Column(name = "end-at")
    private LocalDate endAt;

    @Column(name = "code")
    private String code;

    @Column(name = "movie-id")
    private Integer movieId;

    @Column(name = "name")
    private String name;

    @Column(name = "room-id")
    private Integer roomId;

    @Column(name = "is-active")
    private Boolean isActive;

    @ManyToOne
    @JoinColumn(name = "id_movie")
    @JsonIgnore
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "id_room")
    @JsonIgnore
    private Room room;

    @OneToMany(mappedBy = "schedule")
    private List<Ticket> ticketList;
}
