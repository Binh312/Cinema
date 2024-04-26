package com.example.movie.payload.response;

import lombok.*;

@Getter
@Setter
public class MessageResponse extends RuntimeException{

    private String errorMessage;

    public MessageResponse(String message){
        super();
        this.errorMessage = message;
    }
}
