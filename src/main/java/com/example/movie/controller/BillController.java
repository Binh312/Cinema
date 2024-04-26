package com.example.movie.controller;

import com.example.movie.Entity.Bill;
import com.example.movie.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/bill")
public class BillController {

    @Autowired
    private BillService billService;

    @PostMapping("/all/add-new-bill")
    public ResponseEntity<?> addNewBill(@RequestBody Bill bill,
                                        @RequestParam Integer billTicketId, Integer ticketId, Integer scheduleId,
                                        Integer foodId, Integer billFoodId, Integer promotionId){
        Bill addBill = billService.addNewBill(bill,billTicketId,ticketId,scheduleId,foodId,billFoodId,promotionId);
        return new ResponseEntity<>(addBill, HttpStatus.CREATED);
    }

    @GetMapping("/admin/get-bills-by-cinema")
    public ResponseEntity<?> getBillsByCinema(@RequestBody Integer cinemaId, LocalDate startDate, LocalDate endDate){
        Double totalMoney = billService.getBillsByCinema(cinemaId,startDate,endDate);
        return new ResponseEntity<>(totalMoney, HttpStatus.OK);
    }
}
