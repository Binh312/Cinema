package com.example.movie.service;

import com.example.movie.Entity.Cinema;
import com.example.movie.Entity.Room;
import com.example.movie.payload.response.MessageResponse;
import com.example.movie.repository.CinemaRepository;
import com.example.movie.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private CinemaRepository cinemaRepository;

    public Room addRoom(Room room){
        Optional<Cinema> cinemaOptional = cinemaRepository.findById(room.getCinemaId());
        if (cinemaOptional.isEmpty()){
            throw new MessageResponse("Rạp chiếu phim không tồn tại");
        }

        room.setCinema(cinemaOptional.get());
        room.setCinemaId(cinemaOptional.get().getId());
        room.setIsActive(true);

        return roomRepository.save(room);
    }

    public Room updateRoom(Room room){
        Optional<Room> roomOptional = roomRepository.findById(room.getId());
        if (roomOptional.isEmpty()){
            throw new MessageResponse("Phòng chiếu phim không tồn tại");
        }
        Optional<Cinema> cinemaOptional = cinemaRepository.findById(room.getCinemaId());
        if (cinemaOptional.isEmpty()){
            throw new MessageResponse("Rạp chiếu phim không tồn tại");
        }

        room.setCinema(cinemaOptional.get());
        room.setCinemaId(cinemaOptional.get().getId());

        return roomRepository.save(room);
    }

    public Room deleteRoom(Integer roomId){
        Optional<Room> roomOptional = roomRepository.findById(roomId);
        if (roomOptional.isEmpty()){
            throw new MessageResponse("Phòng chiếu phim không tồn tại");
        }

        roomOptional.get().setIsActive(false);
        return roomRepository.save(roomOptional.get());
    }

    public List<Room> getRoomByCinema(Integer cinemaId){
        return roomRepository.findRoomByCinema(cinemaId);
    }
}
