package com.example.movie.util;

import com.example.movie.security.UserCustomDetail;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
@Component
@Slf4j
public class JwtTokenProvider {

//    private static final String SECRET_KEY = "scretscretvasvaewgvsk";

    SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    //Tao jwt tu thong tin cua user
    public String generateAccessToken(UserCustomDetail userCustomDetail){
        //Tao chuoi jwt tu userName
        return Jwts.builder()
                .setSubject(userCustomDetail.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();
    }

    //Lay thong tin user tu jwt
    public String getUserNameByJwt(String token){
        Claims claims = Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    //Validate thong tin cua jwt
    public boolean validateAccessToken(String token){
        try {
            Jwts.parserBuilder().setSigningKey(key).build()
                    .parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException exception){
            log.error("Invalid JWT Token");
        } catch (ExpiredJwtException exception){
            log.error("Expired JWT Token");
        } catch (UnsupportedJwtException exception){
            log.error("Unsupported JWT Token");
        } catch (IllegalArgumentException exception){
            log.error("JWT claim String is empty");
        }
        return false;
    }

}
