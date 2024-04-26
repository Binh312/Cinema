package com.example.movie.service;

import com.example.movie.Entity.Room;
import com.example.movie.Entity.Seat;
import com.example.movie.Entity.SeatStatus;
import com.example.movie.Entity.SeatType;
import com.example.movie.payload.response.MessageResponse;
import com.example.movie.repository.RoomRepository;
import com.example.movie.repository.SeatRepository;
import com.example.movie.repository.SeatStatusRepository;
import com.example.movie.repository.SeatTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SeatService {

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private SeatStatusRepository seatStatusRepository;

    @Autowired
    private SeatTypeRepository seatTypeRepository;

    @Autowired
    private RoomRepository roomRepository;

    public Seat addSeat(Seat seat){
        Optional<Seat> seatOptionalNumber = seatRepository.findBySeatNumber(seat.getNumber());
        if (seatOptionalNumber.isPresent()){
            throw new MessageResponse("Số ghế "+ seat.getNumber() +"đã tồn tại");
        }
        Optional<SeatStatus> seatStatusOptional = seatStatusRepository.findById(seat.getSeatStatus().getId());
        if (seatStatusOptional.isEmpty()){
            throw new MessageResponse("Trạng thái ghế không tồn tại");
        }
        Optional<SeatType> seatTypeOptional = seatTypeRepository.findById(seat.getSeatType().getId());
        if (seatTypeOptional.isEmpty()){
            throw new MessageResponse("Kiểu ghế không tồn tại");
        }
        Optional<Room> roomOptional = roomRepository.findById(seat.getRoom().getId());
        if (roomOptional.isEmpty()){
            throw new MessageResponse("Phòng chiếu phim không tồn tại");
        }

        seat.setSeatStatusId(seatStatusOptional.get().getId());
        seat.setSeatTypeId(seatTypeOptional.get().getId());
        seat.setRoomId(roomOptional.get().getId());
        seat.setIsActive(true);

        return seatRepository.save(seat);
    }

    public Seat updateSeat(Seat seat){
        Optional<Seat> seatOptionalId = seatRepository.findById(seat.getId());
        if (seatOptionalId.isEmpty()){
            throw new MessageResponse("Ghế có id là "+ seat.getId() +" không tồn tại");
        }
        Optional<Seat> seatOptionalNumber = seatRepository.findBySeatNumber(seat.getNumber());
        if (seatOptionalNumber.isPresent()){
            throw new MessageResponse("Số ghế "+ seat.getNumber() +"đã tồn tại");
        }
        Optional<SeatStatus> seatStatusOptional = seatStatusRepository.findById(seat.getSeatStatus().getId());
        if (seatStatusOptional.isEmpty()){
            throw new MessageResponse("Trạng thái ghế không tồn tại");
        }
        Optional<SeatType> seatTypeOptional = seatTypeRepository.findById(seat.getSeatType().getId());
        if (seatTypeOptional.isEmpty()){
            throw new MessageResponse("Kiểu ghế không tồn tại");
        }
        Optional<Room> roomOptional = roomRepository.findById(seat.getRoom().getId());
        if (roomOptional.isEmpty()){
            throw new MessageResponse("Phòng chiếu phim không tồn tại");
        }

        seat.setSeatStatusId(seatStatusOptional.get().getId());
        seat.setSeatTypeId(seatTypeOptional.get().getId());
        seat.setRoomId(roomOptional.get().getId());
        seat.setIsActive(seatOptionalId.get().getIsActive());

        return seatRepository.save(seat);
    }

    public Seat deleteSeat(Integer seatId){
        Optional<Seat> seatOptional = seatRepository.findById(seatId);
        if (seatOptional.isEmpty()){
            throw new MessageResponse("Ghế có id là "+ seatId +" không tồn tại");
        }

        seatOptional.get().setIsActive(false);
        return seatRepository.save(seatOptional.get());
    }
}
