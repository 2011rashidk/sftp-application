package com.happiest.minds.sftpapplication.service;

import com.happiest.minds.sftpapplication.config.UserManagementBaseURL;
import com.happiest.minds.sftpapplication.request.RolePermissionDTO;
import com.happiest.minds.sftpapplication.response.RolePermission;
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
public class RolePermissionService {

    @Autowired
    UserManagementBaseURL userManagementBaseURL;

    @Autowired
    RedisUtility redisUtility;

    public RolePermission addPermissionToRole(RolePermissionDTO rolePermissionDTO) {
        WebClient webClient = WebClient.create(userManagementBaseURL.getRolePermissionURL());
        return webClient.post()
                .headers(httpHeaders -> httpHeaders.setBearerAuth(redisUtility.getAdminToken()))
                .body(Mono.just(rolePermissionDTO), RolePermission.class)
                .retrieve()
                .bodyToMono(RolePermission.class).block();
    }

    public ResponseEntity<HttpStatus> deleteByRoleIdAndPermissionId(RolePermissionDTO rolePermissionDTO) {
        WebClient webClient = WebClient.create(userManagementBaseURL.getRolePermissionURL());
        ResponseEntity<HttpStatus> response = webClient.method(HttpMethod.DELETE)
                .headers(httpHeaders -> httpHeaders.setBearerAuth(redisUtility.getAdminToken()))
                .body(BodyInserters.fromValue(rolePermissionDTO))
                .retrieve().toEntity(HttpStatus.class).block();
        log.info("deleteByRoleIdAndPermissionId:: status code : {}", response.getStatusCode());
        return response;
    }

}
