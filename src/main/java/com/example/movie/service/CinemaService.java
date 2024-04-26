package com.example.movie.service;

import com.example.movie.Entity.Cinema;
import com.example.movie.payload.response.MessageResponse;
import com.example.movie.repository.CinemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CinemaService {

    @Autowired
    private CinemaRepository cinemaRepository;

    public Cinema addCinema(Cinema cinema){

        cinema.setIsActive(true);

        return cinemaRepository.save(cinema);
    }

    public Cinema updateCinema(Cinema cinema){
        Optional<Cinema> cinemaOptionalId = cinemaRepository.findById(cinema.getId());
        if (cinemaOptionalId.isEmpty()) {
            throw new MessageResponse("Cinema khong ton tai");
        }

        cinema.setIsActive(cinemaOptionalId.get().getIsActive());
        if (cinema.getAddress().isEmpty()){
            cinema.setAddress(cinemaOptionalId.get().getAddress());
        } else {
            cinema.setAddress(cinema.getAddress());
        }

        if (cinema.getDescription().isEmpty()){
            cinema.setDescription(cinemaOptionalId.get().getDescription());
        } else {
            cinema.setDescription(cinema.getDescription());
        }

        if (cinema.getCode().isEmpty()){
            cinema.setCode(cinemaOptionalId.get().getCode());
        } else {
            cinema.setCode(cinema.getCode());
        }

        if (cinema.getNameOfCinema().isEmpty()){
            cinema.setNameOfCinema(cinemaOptionalId.get().getNameOfCinema());
        } else {
            cinema.setNameOfCinema(cinema.getNameOfCinema());
        }
        return cinemaRepository.save(cinema);
    }

    public Cinema deleteCinema(Integer cinemaId){
        Optional<Cinema> cinemaOptional = cinemaRepository.findById(cinemaId);
        if (cinemaOptional.isEmpty()) {
            throw new MessageResponse("Cinema không tồn tại");
        }

        cinemaOptional.get().setIsActive(false);
        return cinemaRepository.save(cinemaOptional.get());
    }

    public List<Cinema> getCinemaByMovie(Integer movieId){
        return cinemaRepository.findCinemasByMovie(movieId);
    }
}
