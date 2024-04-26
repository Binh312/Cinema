package com.example.movie.service;

import com.example.movie.Entity.*;
import com.example.movie.payload.response.MessageResponse;
import com.example.movie.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BillService {

    @Autowired
    private CinemaRepository cinemaRepository;

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private BillTicketRepository billTicketRepository;

    @Autowired
    private BillFoodRepository billFoodRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private PromotionRepository promotionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BillStatusRepository billStatusRepository;

    public Bill addNewBill(Bill bill,Integer billTicketId, Integer ticketId, Integer scheduleId, Integer foodId, Integer billFoodId, Integer promotionId){
        Optional<User> userOptional = userRepository.findById(bill.getCustomerId());
        if (userOptional.isEmpty()){
            throw new MessageResponse("Người dùng không tồn tại");
        }
        Optional<BillStatus> billStatusOptional = billStatusRepository.findById(bill.getBillStatusId());
        if (billStatusOptional.isEmpty()){
            throw new MessageResponse("Trạng thái hoá đơn không tồn tại");
        }
        Optional<BillTicket> billTicketOptional = billTicketRepository.findById(billTicketId);
        if (billTicketOptional.isEmpty()){
            throw new MessageResponse("Hoá đơn vé không tồn tại");
        }
        Optional<Ticket> ticketOptional = ticketRepository.findById(ticketId);
        if (ticketOptional.isEmpty()){
            throw new MessageResponse("Vé không tồn tại");
        }
        Optional<Schedule> scheduleOptional = scheduleRepository.findById(scheduleId);
        if (scheduleOptional.isEmpty()){
            throw new MessageResponse("Lịch chiếu phim không tồn tại");
        }
        Optional<Food> foodOptional = foodRepository.findById(foodId);
        if (foodOptional.isEmpty()){
            throw new MessageResponse("Đồ ăn không tồn tại");
        }
        Optional<BillFood> billFoodOptional = billFoodRepository.findById(billFoodId);
        if (billFoodOptional.isEmpty()){
            throw new MessageResponse("Hoá đơn đồ ăn không tồn tại");
        }
        Optional<Promotion> promotionOptional = promotionRepository.findById(promotionId);
        if (promotionOptional.isEmpty()){
            throw new MessageResponse("Khuyến mãi không tồn tại");
        }

        Double totalPriceBillFood = 0.0;
        totalPriceBillFood = foodOptional.get().getPrice() * billFoodOptional.get().getQuantity();

        Double totalPriceBillTicket = 0.0;
        totalPriceBillTicket = scheduleOptional.get().getPrice() + (billTicketOptional.get().getQuantity() * ticketOptional.get().getPriceTicket());

        Double totalBill = totalPriceBillFood + totalPriceBillTicket;

        Double totalPromotion;
        totalPromotion = (promotionOptional.get().getPercent()/100) * totalBill;

        bill.setCreateTime(LocalDate.now());
        bill.setUpdateTime(LocalDate.now());
        bill.setIsActive(true);
        bill.setCustomerId(userOptional.get().getId());
        bill.setBillStatusId(billStatusOptional.get().getId());
        bill.setPromotionId(promotionOptional.get().getId());
        bill.setTotalMoney(totalBill - totalPromotion);

        return billRepository.save(bill);
    }

    public Double getBillsByCinema(Integer cinemaId, LocalDate startDate, LocalDate endDate){
        Optional<Cinema> cinemaOptional = cinemaRepository.findById(cinemaId);
        if (cinemaOptional.isEmpty()){
            throw new MessageResponse("Rạp chiếu phim không tồn tại");
        }

        Double totalMoney = 0.0;
        List<Bill> billList = billRepository.getBillsByCinema(cinemaId,startDate,endDate);
        for (Bill b : billList) {
            totalMoney += b.getTotalMoney();
        }

        return totalMoney;
    }
}
