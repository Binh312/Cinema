package com.example.movie.controller;

import com.example.movie.Entity.Ticket;
import com.example.movie.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/ticket")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @GetMapping("/all/get-ticket-by-schedule")
    public ResponseEntity<?> getTicketBySchedule(@RequestParam Integer scheduleId){
        List<Ticket> tickets = ticketService.getTicketBySchedule(scheduleId);
        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }
}
