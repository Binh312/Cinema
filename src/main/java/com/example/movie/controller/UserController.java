package com.example.movie.controller;

import com.example.movie.Entity.User;
import com.example.movie.payload.request.LoginRequest;
import com.example.movie.payload.request.RegisRequest;
import com.example.movie.payload.response.TokenResponse;
import com.example.movie.service.UserSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserSerivce userSerivce;

    @PostMapping("/regis")
    public ResponseEntity<?> regis(@RequestBody User user){
        User regisUser = userSerivce.regis(user);
        return new ResponseEntity<>(regisUser, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
        TokenResponse tokenResponse = userSerivce.login(loginRequest);
        return new ResponseEntity<>(tokenResponse, HttpStatus.OK);
    }
}
