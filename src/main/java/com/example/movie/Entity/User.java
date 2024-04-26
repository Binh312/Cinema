package com.example.movie.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "user")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "point")
    private Integer point;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "name")
    private String name;

    @Column(name = "phone-number")
    private String phoneNumber;

    @Column(name = "password")
    @JsonBackReference
    private String password;

    @Column(name = "rank-customer-id")
    private Integer rankCustomerId;

    @Column(name = "user-status-id")
    private Integer userStatusId;

    @Column(name = "is-active")
    private Boolean isActive;

    @Column(name = "role-id")
    private Integer roleId;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Bill> billList;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<ConfirmEmail> confirmEmailList;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<RefreshToken> refreshTockenList;

    @ManyToOne
    @JoinColumn(name = "id_rankcustomer")
    @JsonIgnore
    private RankCustomer rankCustomer;

    @ManyToOne
    @JoinColumn(name = "id_role")
    @JsonIgnore
    private Role role;

    @ManyToOne
    @JoinColumn(name = "id_userstatus")
    @JsonIgnore
    private UserStatus userStatus;
}
