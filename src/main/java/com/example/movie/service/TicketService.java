package com.example.movie.service;

import com.example.movie.Entity.Schedule;
import com.example.movie.Entity.Ticket;
import com.example.movie.payload.response.MessageResponse;
import com.example.movie.repository.ScheduleRepository;
import com.example.movie.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private ScheduleRepository scheduleRepository;

    public List<Ticket> getTicketBySchedule(Integer scheduleId){
        Optional<Schedule> scheduleOptional = scheduleRepository.findById(scheduleId);
        if (scheduleOptional.isEmpty()){
            throw new MessageResponse("Lịch chiếu phim không tồn tại");
        }

        List<Ticket> tickets = ticketRepository.getTicketsBySchedule(scheduleId);
        return tickets;
    }
}
