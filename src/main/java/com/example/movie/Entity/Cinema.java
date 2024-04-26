package com.example.movie.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "cinema")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cinema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "address")
    private String address;

    @Column(name = "desciption")
    private String description;

    @Column(name = "code")
    private String code;

    @Column(name = "name-of-cinema")
    private String nameOfCinema;

    @Column(name = "is-active")
    private Boolean isActive;

    @OneToMany(mappedBy = "cinema", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Room> roomList;
}
