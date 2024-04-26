package com.example.movie.repository;

import com.example.movie.Entity.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CinemaRepository extends JpaRepository<Cinema, Integer> {

    @Query("select c from Cinema c " +
            "join Room r on c.id = r.cinema.id " +
            "join Schedule s on r.id = s.room.id " +
            "join Movie m on m.id = s.movie.id where m.id = ?1 and c.isActive = true")
    List<Cinema> findCinemasByMovie(Integer movieId);
}
