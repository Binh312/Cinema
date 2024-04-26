package com.example.movie.repository;

import com.example.movie.Entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {

    @Query("select s from Schedule s where s.room.id = ?1 and s.isActive = true")
    List<Schedule> getSchedulesByRoom(Integer roomId);
}
