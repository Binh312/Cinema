package com.example.movie.repository;

import com.example.movie.Entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Integer> {

    @Query("select s from Seat s where s.number = ?1 and s.isActive = true")
    Optional<Seat> findBySeatNumber(Integer seatNumber);
}
