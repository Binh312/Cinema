package com.example.movie.payload.mapper;

import com.example.movie.Entity.User;
import com.example.movie.payload.request.RegisRequest;
import org.springframework.stereotype.Service;

@Service
public class LoginMapper {

    public User convertLoginRequestToUser(RegisRequest request){
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());

        return user;
    }
}
