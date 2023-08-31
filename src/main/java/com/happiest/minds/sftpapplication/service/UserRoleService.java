package com.happiest.minds.sftpapplication.service;

import com.happiest.minds.sftpapplication.config.UserManagementBaseURL;
import com.happiest.minds.sftpapplication.request.UserRoleDTO;
import com.happiest.minds.sftpapplication.response.UserRole;
import com.happiest.minds.sftpapplication.utility.RedisUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class UserRoleService {


    @Autowired
    UserManagementBaseURL userManagementBaseURL;

    @Autowired
    RedisUtility redisUtility;

    public UserRole addRoleToUser(UserRoleDTO userRoleDTO) {
        WebClient webClient = WebClient.create(userManagementBaseURL.getUserRoleURL());
        return webClient.post()
                .headers(httpHeaders -> httpHeaders.setBearerAuth(redisUtility.getAdminToken()))
                .body(Mono.just(userRoleDTO), UserRole.class)
                .retrieve()
                .bodyToMono(UserRole.class).block();
    }

    public ResponseEntity<HttpStatus> deleteByUserIdAndRoleId(UserRoleDTO userRoleDTO) {
        WebClient webClient = WebClient.create(userManagementBaseURL.getUserRoleURL());
        ResponseEntity<HttpStatus> response = webClient.method(HttpMethod.DELETE)
                .headers(httpHeaders -> httpHeaders.setBearerAuth(redisUtility.getAdminToken()))
                .body(BodyInserters.fromValue(userRoleDTO))
                .retrieve().toEntity(HttpStatus.class).block();
        log.info("deleteByRoleIdAndPermissionId:: status code : {}", response.getStatusCode());
        return response;
    }

}
