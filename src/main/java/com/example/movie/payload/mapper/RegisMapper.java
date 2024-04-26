package com.example.movie.payload.mapper;

import com.example.movie.Entity.User;
import com.example.movie.payload.request.RegisRequest;
import org.springframework.stereotype.Service;

@Service
public class RegisMapper {

    public User convertRegisRequestToUser(RegisRequest request){
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setEmail(request.getEmail());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setName(request.getName());
        return user;
    }
}
