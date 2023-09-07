package com.happiest.minds.sftpapplication.service;

import com.happiest.minds.sftpapplication.config.UserManagementBaseURL;
import com.happiest.minds.sftpapplication.exception.DataNotFoundException;
import com.happiest.minds.sftpapplication.request.PermissionDTO;
import com.happiest.minds.sftpapplication.response.Permission;
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
public class PermissionService {

    @Autowired
    UserManagementBaseURL userManagementBaseURL;

    @Autowired
    RedisUtility redisUtility;

    public Permission createPermission(PermissionDTO permissionDTO) {
        WebClient webClient = WebClient.create(userManagementBaseURL.getPermissionURL());
        return webClient.post()
                .headers(httpHeaders -> httpHeaders.setBearerAuth(redisUtility.getAdminToken()))
                .body(Mono.just(permissionDTO), Permission.class)
                .retrieve()
                .bodyToMono(Permission.class).block();
    }

    public Permission getPermissionById(Integer permissionId) {
        WebClient webClient = WebClient.create(userManagementBaseURL.getPermissionURL());
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(ID_PLACE_HOLDER.getValue())
                        .build(permissionId))
                .headers(httpHeaders -> httpHeaders.setBearerAuth(redisUtility.getAdminToken()))
                .retrieve()
                .bodyToMono(Permission.class)
                .onErrorResume(WebClientResponseException.class, e -> {
                    if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                        return Mono.error(new DataNotFoundException(ID_NOT_FOUND.getValue()));
                    } else {
                        return Mono.error(new Exception(e.getMessage()));
                    }
                })
                .block();
    }

    public List<Permission> getAllPermissions() {
        WebClient webClient = WebClient.create(userManagementBaseURL.getPermissionURL());
        return webClient.get()
                .headers(httpHeaders -> httpHeaders.setBearerAuth(redisUtility.getAdminToken()))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Permission>>() {
                })
                .block();
    }

    public Permission updatePermissionById(Integer permissionId, PermissionDTO permissionDTO) {
        WebClient webClient = WebClient.create(userManagementBaseURL.getPermissionURL());
        return webClient.put()
                .uri(uriBuilder -> uriBuilder
                        .path(ID_PLACE_HOLDER.getValue())
                        .build(permissionId))
                .headers(httpHeaders -> httpHeaders.setBearerAuth(redisUtility.getAdminToken()))
                .body(Mono.just(permissionDTO), Permission.class)
                .retrieve()
                .bodyToMono(Permission.class)
                .onErrorResume(WebClientResponseException.class, e -> {
                    if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                        return Mono.error(new DataNotFoundException(ID_NOT_FOUND.getValue()));
                    } else {
                        return Mono.error(new Exception(e.getMessage()));
                    }
                })
                .block();
    }

    public ResponseEntity<HttpStatus> deletePermissionById(Integer permissionId) {
        WebClient webClient = WebClient.create(userManagementBaseURL.getPermissionURL());
        ResponseEntity<HttpStatus> response = webClient.delete()
                .uri(uriBuilder -> uriBuilder
                        .path(ID_PLACE_HOLDER.getValue())
                        .build(permissionId))
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
        log.info("deletePermissionById:: status code : {}", response.getStatusCode());
        return response;
    }

}
