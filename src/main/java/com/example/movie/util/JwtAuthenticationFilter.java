package com.example.movie.util;

import com.example.movie.security.UserCustomDetail;
import com.example.movie.security.UserCustomDetailService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserCustomDetailService userCustomDetailService;

    private String hasAuthorizationBearer(HttpServletRequest request){
        String bearerToken = request.getHeader("Authorization");
        //Kiểm tra xem header Authorization có chứa thông tin jwt không
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")){
            return bearerToken.substring(7);
        }
        return null;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            //Lấy JWT từ request
            String jwt = hasAuthorizationBearer(request);
            if (StringUtils.hasText(jwt) && jwtTokenProvider.validateAccessToken(jwt)){
                //Lấy username từ chuỗi jwt
                String userName = jwtTokenProvider.getUserNameByJwt(jwt);
                //Lấy thông tin ng dùng từ userId
                UserDetails userDetails = userCustomDetailService.loadUserByUsername(userName);
                if (userDetails != null) {
                    //Nếu ng dùng hợp lệ set thông tin cho security context
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetails,null, userDetails.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        } catch (Exception exception){
            log.error("Fail on set user authentication", exception);
        }
        filterChain.doFilter(request,response);
    }
}
