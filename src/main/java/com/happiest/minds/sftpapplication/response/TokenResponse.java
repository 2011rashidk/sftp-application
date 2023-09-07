package com.happiest.minds.sftpapplication.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TokenResponse {

    private String accessToken;
    private String refreshToken;
    private LocalDateTime accessTokenExpiry;
    private LocalDateTime refreshTokenExpiry;
    private String tokenSetToCache;
}
