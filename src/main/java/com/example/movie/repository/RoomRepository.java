package com.example.movie.repository;

import com.example.movie.Entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {

    @Query("select r from Room r where r.cinema.id = ?1 and r.isActive = true")
    List<Room> findRoomByCinema(Integer cinemaId);
}
