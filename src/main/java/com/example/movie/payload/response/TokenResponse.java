package com.example.movie.payload.response;

import com.example.movie.Entity.User;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TokenResponse {

    private String token;

    private User user;
}
