package com.example.movie.service;

import com.example.movie.Entity.Room;
import com.example.movie.Entity.Schedule;
import com.example.movie.payload.response.MessageResponse;
import com.example.movie.repository.RoomRepository;
import com.example.movie.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private RoomRepository roomRepository;

    public List<Schedule> getScheduleByRoom(Integer roomId){
        Optional<Room> roomOptional = roomRepository.findById(roomId);
        if (roomOptional.isEmpty()){
            throw new MessageResponse("Phòng không tồn tại");
        }

        List<Schedule> schedules = scheduleRepository.getSchedulesByRoom(roomId);
        return schedules;
    }
}
