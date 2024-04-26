package com.example.movie.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "ticket")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "code")
    private String code;

    @Column(name = "schedule-id")
    private Integer scheduleId;

    @Column(name = "seat-id")
    private Integer seatId;

    @Column(name = "price-ticket")
    private Double priceTicket;

    @Column(name = "is-active")
    private Boolean isActive;

    @OneToMany(mappedBy = "ticket")
    @JsonIgnore
    private List<BillTicket> billTicketList;

    @ManyToOne
    @JoinColumn(name = "id_schedule")
    @JsonIgnore
    private Schedule schedule;

    @ManyToOne
    @JoinColumn(name = "id_seat")
    @JsonIgnore
    private Seat seat;
}
