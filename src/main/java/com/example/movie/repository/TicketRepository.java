package com.example.movie.repository;

import com.example.movie.Entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {

    @Query("select t from Ticket t where t.schedule.id = ?1 and t.isActive = true ")
    List<Ticket> getTicketsBySchedule(Integer scheduleId);
}
