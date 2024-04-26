package com.example.movie.controller;

import com.example.movie.Entity.Cinema;
import com.example.movie.service.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cinema")
public class CinemaController {

    @Autowired
    private CinemaService cinemaService;

    @PostMapping("/admin/add-cinema")
    public ResponseEntity<?> addCinema(@RequestBody Cinema cinema){
        Cinema addCinema = cinemaService.addCinema(cinema);
        return new ResponseEntity<>(addCinema, HttpStatus.CREATED);
    }

    @PutMapping("/admin/update-cinema")
    public ResponseEntity<?> updateCinema(@RequestBody Cinema cinema){
        Cinema updateCinema = cinemaService.updateCinema(cinema);
        return new ResponseEntity<>(updateCinema, HttpStatus.OK);
    }

    @DeleteMapping("/admin/delete-cinema/{cinemaId}")
    public ResponseEntity<?> deleteCinema(@PathVariable Integer cinemaId){
        Cinema deleteCinema = cinemaService.deleteCinema(cinemaId);
        return new ResponseEntity<>(deleteCinema, HttpStatus.CREATED);
    }

    @GetMapping("/all/get-cinema-by-movie")
    public ResponseEntity<?> getCinemaByMovie(@RequestParam Integer movieId){
        List<Cinema> cinemas = cinemaService.getCinemaByMovie(movieId);
        return new ResponseEntity<>(cinemas, HttpStatus.OK);
    }
}
