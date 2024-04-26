package com.example.movie.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "promotion")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Promotion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "percent")
    private Double percent;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "type")
    private String type;

    @Column(name = "start-time")
    private LocalDate startTime;

    @Column(name = "end-time")
    private LocalDate endTime;

    @Column(name = "description")
    private String description;

    @Column(name = "name")
    private String name;

    @Column(name = "rank-customer-id")
    private Integer rankCustomerId;

    @Column(name = "is-active")
    private Boolean isActive;

    @OneToMany(mappedBy = "promotion")
    @JsonIgnore
    private List<Bill> billList;

    @ManyToOne
    @JoinColumn(name = "id_rankcustomer")
    @JsonIgnore
    private RankCustomer rankCustomer;
}
