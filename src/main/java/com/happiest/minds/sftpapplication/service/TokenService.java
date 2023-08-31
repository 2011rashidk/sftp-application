package com.happiest.minds.sftpapplication.service;

import com.happiest.minds.sftpapplication.config.UserManagementBaseURL;
import com.happiest.minds.sftpapplication.request.TokenDTO;
import com.happiest.minds.sftpapplication.response.TokenResponse;
import lombok.extern.slf4j.Slf4j;
import org.example.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class TokenService {

    @Autowired
    UserManagementBaseURL userManagementBaseURL;

    public TokenResponse getToken(TokenDTO tokenDTO) {
        RedisUtil redisUtil = new RedisUtil();
        WebClient webClient = WebClient.create(userManagementBaseURL.getLoginURL());
        TokenResponse tokenResponse = webClient.post()
                .body(Mono.just(tokenDTO), TokenResponse.class)
                .retrieve()
                .bodyToMono(TokenResponse.class).block();
        log.info("loginResponse: {}", tokenResponse);
        if (tokenResponse != null) {
            String setCacheResponse = redisUtil.set(tokenResponse.getUsername(), tokenResponse.getJwtToken());
            tokenResponse.setTokenSetToCache(setCacheResponse);
            log.info("Redis cache:- response: {}", setCacheResponse);
        }
        return tokenResponse;
    }

}
