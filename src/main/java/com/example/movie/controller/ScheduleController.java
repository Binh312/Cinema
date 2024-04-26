package com.example.movie.controller;

import com.example.movie.Entity.Schedule;
import com.example.movie.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/schedule")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @GetMapping("/all/get-schedule-by-room")
    public ResponseEntity<?> getScheduleByRoom(@RequestParam Integer roomId){
        List<Schedule> schedules = scheduleService.getScheduleByRoom(roomId);
        return new ResponseEntity<>(schedules, HttpStatus.OK);
    }
}
