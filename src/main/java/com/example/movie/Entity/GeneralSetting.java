package com.example.movie.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "general-setting")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GeneralSetting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "break-time")
    private LocalDate breakTime;

    @Column(name = "business-hours")
    private Integer businessHours;

    @Column(name = "close-time")
    private LocalDate closeTime;

    @Column(name = "fixed-ticket-price")
    private Double fixedTicketPrice;

    @Column(name = "percent-day")
    private Integer percentDay;

    @Column(name = "percent-weekend")
    private Integer percentWeekend;

    @Column(name = "time-begin-to-change")
    private LocalDate timeBeginToChange;
}
