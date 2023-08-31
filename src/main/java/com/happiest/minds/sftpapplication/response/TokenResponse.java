package com.happiest.minds.sftpapplication.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TokenResponse {

    private String username;
    private String jwtToken;
    private LocalDateTime tokenExpiryTime;
    private String tokenSetToCache;
}
