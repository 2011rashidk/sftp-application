package com.happiest.minds.sftpapplication.service;

import com.happiest.minds.sftpapplication.config.UserManagementBaseURL;
import com.happiest.minds.sftpapplication.exception.DataNotFoundException;
import com.happiest.minds.sftpapplication.request.TokenDTO;
import com.happiest.minds.sftpapplication.response.TokenResponse;
import lombok.extern.slf4j.Slf4j;
import org.example.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import static com.happiest.minds.sftpapplication.enums.Constants.*;

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
                .bodyToMono(TokenResponse.class)
                .onErrorResume(WebClientResponseException.class, e -> {
                    log.error(e.getResponseBodyAsString());
                    if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                        return Mono.error(new DataNotFoundException(USERNAME_NOT_FOUND.getValue()));
                    } else {
                        return Mono.error(new Exception(e.getResponseBodyAsString()));
                    }
                })
                .block();
        log.info("loginResponse: {}", tokenResponse);
        if (tokenResponse != null) {
            String setCacheResponse = redisUtil.set(tokenDTO.getUsername(), tokenResponse.getAccessToken());
            tokenResponse.setTokenSetToCache(setCacheResponse);
            log.info("Redis cache:- response: {}", setCacheResponse);
        }
        return tokenResponse;
    }

}
