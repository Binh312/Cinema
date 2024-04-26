package com.example.movie.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "rank-customer")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RankCustomer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "point")
    private Integer point;

    @Column(name = "description")
    private String description;

    @Column(name = "name")
    private String name;

    @Column(name = "is-active")
    private Boolean isActive;

    @OneToMany(mappedBy = "rankCustomer")
    @JsonIgnore
    private List<Promotion> promotionList;

    @OneToMany(mappedBy = "rankCustomer")
    private List<User> userList;

    public RankCustomer(Integer rankCustomerid) {
        this.id = rankCustomerid;
    }
}
