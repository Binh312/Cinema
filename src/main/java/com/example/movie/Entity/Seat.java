package com.example.movie.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "seat")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "number")
    private Integer number;

    @Column(name = "seat-status-id")
    private Integer seatStatusId;

    @Column(name = "line")
    private String line;

    @Column(name = "room-id")
    private Integer roomId;

    @Column(name = "seat-type-id")
    private Integer seatTypeId;

    @Column(name = "is-active")
    private Boolean isActive;

    @ManyToOne
    @JoinColumn(name = "id_room")
    @JsonIgnore
    private Room room;

    @ManyToOne
    @JoinColumn(name = "id_seatstatus")
    @JsonIgnore
    private SeatStatus seatStatus;

    @ManyToOne
    @JoinColumn(name = "id_seattype")
    @JsonIgnore
    private SeatType seatType;

    @OneToMany(mappedBy = "seat", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Ticket> ticketList;
}
