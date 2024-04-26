package com.example.movie.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "bill-tichket")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BillTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "bill-id")
    private Integer billId;

    @Column(name = "ticket-id")
    private Integer ticketId;

    @ManyToOne
    @JoinColumn(name = "id_bill")
    @JsonIgnore
    private Bill bill;

    @ManyToOne
    @JoinColumn(name = "id_ticket")
    @JsonIgnore
    private Ticket ticket;
}
