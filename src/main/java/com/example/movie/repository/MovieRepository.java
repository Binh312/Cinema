package com.example.movie.repository;

import com.example.movie.Entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {

    @Query("select m from Movie m " +
            "join Schedule s on m.id = s.movie.id " +
            "join Room r on s.room.id = r.id " +
            "join Cinema c on c.id = r.cinema.id where c.id = ?1 or c.nameOfCinema like %?2%")
    List<Movie> findMovieByCinema(Integer cinemaId, String nameOfCinema);

    @Query("select m from Movie m " +
            "join Schedule s on m.id = s.movie.id " +
            "join Room r on s.room.id = r.id where r.id = ?1 or r.name like %?2%")
    List<Movie> findMovieByRoom(Integer roomId, String nameOfRoom);


}
