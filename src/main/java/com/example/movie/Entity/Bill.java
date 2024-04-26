package com.example.movie.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "bill")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "total-money")
    private Double totalMoney;

    @Column(name = "trading-code")
    private String tradingCode;

    @Column(name = "create-time")
    private LocalDate createTime;

    @Column(name = "customer-id")
    private Integer customerId;

    @Column(name = "name")
    private String name;

    @Column(name = "update-time")
    private LocalDate updateTime;

    @Column(name = "promotion-id")
    private Integer promotionId;

    @Column(name = "bill-status-id")
    private Integer billStatusId;

    @Column(name = "is-active")
    private Boolean isActive;

    @ManyToOne
    @JoinColumn(name = "id_billstatus")
    @JsonIgnore
    private BillStatus billStatus;

    @ManyToOne
    @JoinColumn(name = "id_user")
    @JsonIgnore
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_promotion")
    @JsonIgnore
    private Promotion promotion;

    @OneToMany(mappedBy = "bill")
    @JsonIgnore
    private List<BillFood> billFoodList;

    @OneToMany(mappedBy = "bill")
    @JsonIgnore
    private List<BillTicket> billTicketList;
}
