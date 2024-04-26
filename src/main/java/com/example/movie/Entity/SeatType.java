package com.example.movie.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "seat-type")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SeatType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name-type")
    private String nameType;

    @OneToMany(mappedBy = "seatType")
    @JsonIgnore
    private List<Seat> seatList;
}
