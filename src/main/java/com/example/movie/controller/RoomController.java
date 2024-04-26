package com.example.movie.controller;

import com.example.movie.Entity.Room;
import com.example.movie.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/room")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @PostMapping("/admin/add-room")
    public ResponseEntity<?> addRoom(@RequestBody Room room){
        Room addRoom = roomService.addRoom(room);
        return new ResponseEntity<>(addRoom, HttpStatus.CREATED);
    }

    @PutMapping("/admin/update-room")
    public ResponseEntity<?> updateRoom(@RequestBody Room room){
        Room upadteRoom = roomService.updateRoom(room);
        return new ResponseEntity<>(upadteRoom, HttpStatus.CREATED);
    }

    @DeleteMapping("/admin/delete-room")
    public ResponseEntity<?> deleteRoom(@RequestParam Integer roomId){
        Room deleteRoom = roomService.deleteRoom(roomId);
        return new ResponseEntity<>(deleteRoom, HttpStatus.OK);
    }

    @GetMapping("/all/get-room-by-cinema")
    public ResponseEntity<?> getRoomByCinema(@RequestParam Integer cinemaId){
        List<Room> rooms = roomService.getRoomByCinema(cinemaId);
        return new ResponseEntity<>(rooms, HttpStatus.OK);
    }
}
