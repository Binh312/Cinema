package com.example.movie.controller;

import com.example.movie.Entity.Movie;
import com.example.movie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/movie")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping("/admin/add-movie")
    public ResponseEntity<?> addMovie(@RequestBody Movie movie, MultipartFile imageMovie, MultipartFile imageHero) throws IOException {
        Movie addMovie = movieService.addMovie(movie, imageMovie, imageHero);
        return new ResponseEntity<>(addMovie, HttpStatus.CREATED);
    }

    @PutMapping("/admin/update-movie")
    public ResponseEntity<?> updateMovie(@RequestBody Movie movie, MultipartFile imageMovie, MultipartFile imageHero) throws IOException {
        Movie updateMovie = movieService.addMovie(movie, imageMovie, imageHero);
        return new ResponseEntity<>(updateMovie, HttpStatus.CREATED);
    }

    @DeleteMapping("/admin/delete-movie")
    public ResponseEntity<?> deleteMovie(@RequestParam Integer movieId){
        Movie deleteMovie = movieService.deleteMovie(movieId);
        return new ResponseEntity<>(deleteMovie, HttpStatus.OK);
    }

    @GetMapping("/admin/get-movie-by-cinema")
    public ResponseEntity<?> getMovieByCinema(@RequestBody Integer cinemaId, String nameOfCinema){
        List<Movie> movieList = movieService.findMovieByCinema(cinemaId, nameOfCinema);
        return new ResponseEntity<>(movieList, HttpStatus.OK);
    }

    @GetMapping("/admin/get-movie-by-room")
    public ResponseEntity<?> getMovieByRoom(@RequestBody Integer roomId, String nameOfRoom){
        List<Movie> movieList = movieService.findMovieByRoom(roomId, nameOfRoom);
        return new ResponseEntity<>(movieList, HttpStatus.OK);
    }
}
