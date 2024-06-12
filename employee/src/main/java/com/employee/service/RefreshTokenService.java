package com.employee.service;

import com.employee.entity.RefreshToken;
import com.employee.repository.RefreshTokenRepository;
import com.employee.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenService {

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private UserRepository userRepository;

    public RefreshToken createRefreshToken(String username){

        RefreshToken refreshToken = RefreshToken.builder()
                .userInfo(userRepository.findByUsername(username).get())
                .token(UUID.randomUUID().toString())
                .expiryDate(new Date(System.currentTimeMillis() + 120000)) // set expiry of refresh token to 2 minutes - you can configure it application.properties file
                .build();
        return refreshTokenRepository.save(refreshToken);
    }



    public Optional<RefreshToken> findByToken(String token){
        return refreshTokenRepository.findByToken(token);
    }

    public RefreshToken verifyExpiration(RefreshToken token){
        if(token.getExpiryDate().before(new Date(System.currentTimeMillis()))){
            refreshTokenRepository.delete(token);
            throw new RuntimeException(token.getToken() + " Refresh token is expired. Please make a new login..!");
        }
        return token;
    }

}
