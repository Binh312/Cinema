package com.example.movie.repository;

import com.example.movie.Entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<Bill, Integer> {

    @Query("select b from Bill b " +
            "join BillTicket bticket on bticket.bill.id = b.id " +
            "join Ticket t on bticket.ticket.id = t.id " +
            "join Schedule s on t.schedule.id = s.id " +
            "join Room r on s.room.id = r.id " +
            "join Cinema c on r.cinema.id = c.id where c.id = ?1 and b.isActive = true and b.createTime between ?2 and ?3")
    List<Bill> getBillsByCinema(Integer cinemaId, LocalDate startDate, LocalDate endDate);
}
