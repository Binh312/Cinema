package com.example.movie.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "seat-status")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SeatStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "code")
    private String code;

    @Column(name = "name-status")
    private String nameStatus;

    @OneToMany(mappedBy = "seatStatus")
    @JsonIgnore
    private List<Seat> seatList;
}
