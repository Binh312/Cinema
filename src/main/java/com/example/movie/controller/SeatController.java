package com.example.movie.controller;

import com.example.movie.Entity.Seat;
import com.example.movie.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/seat")
public class SeatController {

    @Autowired
    private SeatService seatService;

    @PostMapping("/admin/add-seat")
    public ResponseEntity<?> addSeat(@RequestBody Seat seat){
        Seat addSeat = seatService.addSeat(seat);
        return new ResponseEntity<>(addSeat, HttpStatus.CREATED);
    }

    @PutMapping("/admin/update-seat")
    public ResponseEntity<?> updateSeat(@RequestBody Seat seat){
        Seat updateSeat = seatService.updateSeat(seat);
        return new ResponseEntity<>(updateSeat, HttpStatus.CREATED);
    }

    @DeleteMapping("/admin/delete-seat")
    public ResponseEntity<?> deleteSeat(@RequestParam Integer seatId){
        Seat deleteSeat = seatService.deleteSeat(seatId);
        return new ResponseEntity<>(deleteSeat, HttpStatus.OK);
    }
}
