package com.happiest.minds.sftpapplication.service;

import com.happiest.minds.sftpapplication.config.UserManagementBaseURL;
import com.happiest.minds.sftpapplication.exception.DataNotFoundException;
import com.happiest.minds.sftpapplication.request.RoleDTO;
import com.happiest.minds.sftpapplication.response.Role;
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
public class RoleService {

    @Autowired
    UserManagementBaseURL userManagementBaseURL;

    @Autowired
    RedisUtility redisUtility;

    public Role createRole(RoleDTO roleDTO) {
        WebClient webClient = WebClient.create(userManagementBaseURL.getRoleURL());
        return webClient.post()
                .headers(httpHeaders -> httpHeaders.setBearerAuth(redisUtility.getAdminToken()))
                .body(Mono.just(roleDTO), Role.class)
                .retrieve()
                .bodyToMono(Role.class).block();
    }

    public Role getRoleById(Integer roleId) {
        WebClient webClient = WebClient.create(userManagementBaseURL.getRoleURL());
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(ID_PLACE_HOLDER.getValue())
                        .build(roleId))
                .headers(httpHeaders -> httpHeaders.setBearerAuth(redisUtility.getAdminToken()))
                .retrieve()
                .bodyToMono(Role.class)
                .onErrorResume(WebClientResponseException.class, e -> {
                    if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                        return Mono.error(new DataNotFoundException(ID_NOT_FOUND.getValue()));
                    } else {
                        return Mono.error(new Exception(e.getMessage()));
                    }
                })
                .block();
    }

    public List<Role> getAllRoles() {
        WebClient webClient = WebClient.create(userManagementBaseURL.getRoleURL());
        return webClient.get()
                .headers(httpHeaders -> httpHeaders.setBearerAuth(redisUtility.getAdminToken()))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Role>>() {
                })
                .block();
    }

    public Role updateRoleById(Integer roleId, RoleDTO roleDTO) {
        WebClient webClient = WebClient.create(userManagementBaseURL.getRoleURL());
        return webClient.put()
                .uri(uriBuilder -> uriBuilder
                        .path(ID_PLACE_HOLDER.getValue())
                        .build(roleId))
                .headers(httpHeaders -> httpHeaders.setBearerAuth(redisUtility.getAdminToken()))
                .body(Mono.just(roleDTO), Role.class)
                .retrieve()
                .bodyToMono(Role.class).block();
    }

    public ResponseEntity<HttpStatus> deleteRoleById(Integer roleId) {
        WebClient webClient = WebClient.create(userManagementBaseURL.getRoleURL());
        ResponseEntity<HttpStatus> response = webClient.delete()
                .uri(uriBuilder -> uriBuilder
                        .path(ID_PLACE_HOLDER.getValue())
                        .build(roleId))
                .headers(httpHeaders -> httpHeaders.setBearerAuth(redisUtility.getAdminToken()))
                .retrieve().toEntity(HttpStatus.class).block();
        log.info("deleteRoleById:: status code : {}", response.getStatusCode());
        return response;
    }

}
