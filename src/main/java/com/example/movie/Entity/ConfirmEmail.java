package com.example.movie.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "confirm-email")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConfirmEmail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "user-id")
    private Integer userId;

    @Column(name = "expired-time")
    private LocalDateTime expiredTime;

    @Column(name = "confirm-code")
    private String confirmCode;

    @Column(name = "is-confirm")
    private Boolean isConfirm;

    @ManyToOne
    @JoinColumn(name = "id_user")
    @JsonIgnore
    private User user;
}
