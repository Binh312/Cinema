package com.example.movie.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "room")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "capacity")
    private Integer capacity;

    @Column(name = "type")
    private Integer Type;

    @Column(name = "description")
    private String description;

    @Column(name = "ciname-id")
    private Integer cinemaId;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "is-active")
    private Boolean isActive;

    @ManyToOne
    @JoinColumn(name = "id_cinema")
    @JsonIgnore
    private Cinema cinema;

    @OneToMany(mappedBy = "room",cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Schedule> scheduleList;

    @OneToMany(mappedBy = "room",cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Seat> seatList;
}
