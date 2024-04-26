package com.example.movie.payload.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisRequest {
    private String username;
    private String password;
    private String email;
    private String phoneNumber;
    private String name;
}
