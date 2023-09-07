package com.happiest.minds.sftpapplication.service;

import com.happiest.minds.sftpapplication.config.UserManagementBaseURL;
import com.happiest.minds.sftpapplication.exception.DataNotFoundException;
import com.happiest.minds.sftpapplication.request.UserDTO;
import com.happiest.minds.sftpapplication.response.User;
import com.happiest.minds.sftpapplication.utility.RedisUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.util.List;

import static com.happiest.minds.sftpapplication.enums.Constants.*;

@Service
@Slf4j
public class UserService {

    @Autowired
    UserManagementBaseURL userManagementBaseURL;

    @Autowired
    RedisUtility redisUtility;

    public User createUser(UserDTO userDTO) {
        WebClient webClient = WebClient.create(userManagementBaseURL.getUserURL());
        return webClient.post()
                .headers(httpHeaders -> httpHeaders.setBearerAuth(redisUtility.getAdminToken()))
                .body(Mono.just(userDTO), User.class)
                .retrieve()
                .bodyToMono(User.class).block();
    }

    public User getUserById(Integer userId) {
        WebClient webClient = WebClient.create(userManagementBaseURL.getUserURL());
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(ID_PLACE_HOLDER.getValue())
                        .build(userId))
                .headers(httpHeaders -> httpHeaders.setBearerAuth(redisUtility.getAdminToken()))
                .retrieve()
                .bodyToMono(User.class)
                .onErrorResume(WebClientResponseException.class, e -> {
                    if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                        return Mono.error(new DataNotFoundException(ID_NOT_FOUND.getValue()));
                    } else {
                        return Mono.error(new Exception(e.getMessage()));
                    }
                })
                .block();
    }

    public List<User> getAllUsers() {
        WebClient webClient = WebClient.create(userManagementBaseURL.getUserURL());
        return webClient.get()
                .headers(httpHeaders -> httpHeaders.setBearerAuth(redisUtility.getAdminToken()))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<User>>() {
                })
                .block();
    }

    public User updateUserById(Integer roleId, UserDTO userDTO) {
        WebClient webClient = WebClient.create(userManagementBaseURL.getUserURL());
        return webClient.put()
                .uri(uriBuilder -> uriBuilder
                        .path(ID_PLACE_HOLDER.getValue())
                        .build(roleId))
                .headers(httpHeaders -> httpHeaders.setBearerAuth(redisUtility.getAdminToken()))
                .body(Mono.just(userDTO), User.class)
                .retrieve()
                .bodyToMono(User.class)
                .onErrorResume(WebClientResponseException.class, e -> {
                    if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                        return Mono.error(new DataNotFoundException(ID_NOT_FOUND.getValue()));
                    } else {
                        return Mono.error(new Exception(e.getMessage()));
                    }
                })
                .block();
    }

    public ResponseEntity<HttpStatus> deleteUserById(Integer userId) {
        WebClient webClient = WebClient.create(userManagementBaseURL.getUserURL());
        ResponseEntity<HttpStatus> response = webClient.delete()
                .uri(uriBuilder -> uriBuilder
                        .path(ID_PLACE_HOLDER.getValue())
                        .build(userId))
                .headers(httpHeaders -> httpHeaders.setBearerAuth(redisUtility.getAdminToken()))
                .retrieve().toEntity(HttpStatus.class)
                .onErrorResume(WebClientResponseException.class, e -> {
                    if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                        return Mono.error(new DataNotFoundException(ID_NOT_FOUND.getValue()));
                    } else {
                        return Mono.error(new Exception(e.getMessage()));
                    }
                })
                .block();
        log.info("deleteRoleById:: status code : {}", response.getStatusCode());
        return response;
    }

}
