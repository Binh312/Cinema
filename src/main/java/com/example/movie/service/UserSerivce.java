package com.example.movie.service;

import com.example.movie.Entity.*;
import com.example.movie.payload.request.LoginRequest;
import com.example.movie.payload.response.MessageResponse;
import com.example.movie.payload.response.TokenResponse;
import com.example.movie.repository.*;
import com.example.movie.security.UserCustomDetail;
import com.example.movie.util.GenerateCodeUtil;
import com.example.movie.util.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserSerivce {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ConfirmEmailRepository confirmEmailRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;


    @Autowired
    private PasswordEncoder passwordEncoder;

    public User regis(User user) {
        Optional<User> userOptionalUsername = userRepository.findByUsername(user.getUsername());
        if (userOptionalUsername.isPresent()) {
            throw new MessageResponse("Tên tài koản đã tồn tại");
        }

        Optional<User> userOptionalEmail = userRepository.findByEmail(user.getEmail());
        if (userOptionalEmail.isPresent()) {
            throw new MessageResponse("Email đã tồn tại");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setPoint(0);
        user.setRankCustomerId(1);
        user.setUserStatusId(1);
        user.setIsActive(true);
        user.setRoleId(2);
        user.setRankCustomer(new RankCustomer(1));
        user.setUserStatus(new UserStatus(1));
        user.setRole(new Role(2));

        String confirmCode = GenerateCodeUtil.generateCode();

        ConfirmEmail confirmEmail = new ConfirmEmail();
        confirmEmail.setUserId(user.getId());
        confirmEmail.setIsConfirm(false);
        confirmEmail.setConfirmCode(confirmCode);
        confirmEmail.setExpiredTime(LocalDateTime.now().plusMinutes(5));
        confirmEmail.setUser(user);

        confirmEmailRepository.save(confirmEmail);

        emailService.sendEmail(user.getEmail(), "Kích hoạt tài khoản", confirmCode);

        return userRepository.save(user);
    }

    public TokenResponse login(LoginRequest loginRequest){
        Optional<User> userOptional = userRepository.findByUsername(loginRequest.getUsername());
        if (userOptional.isEmpty()) {
            throw new MessageResponse("Tài khoản không tồn tại");
        }
        if (passwordEncoder.matches(loginRequest.getPassword(), userOptional.get().getPassword()) == false){
            throw new MessageResponse("Mật khẩu không chính xác");
        }
        if (userOptional.get().getIsActive() == false){
            throw new MessageResponse("Tài khoản đã bị khoá");
        }

        UserCustomDetail userCustomDetail =new UserCustomDetail(userOptional.get());
        String token = jwtTokenProvider.generateAccessToken(userCustomDetail);
        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setToken(token);
        tokenResponse.setUser(userOptional.get());

        return tokenResponse;
    }
}
