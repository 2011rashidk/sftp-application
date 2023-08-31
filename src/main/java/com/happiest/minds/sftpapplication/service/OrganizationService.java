package com.happiest.minds.sftpapplication.service;

import com.happiest.minds.sftpapplication.config.UserManagementBaseURL;
import com.happiest.minds.sftpapplication.request.OrganizationDTO;
import com.happiest.minds.sftpapplication.response.Organization;
import com.happiest.minds.sftpapplication.utility.RedisUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

import static com.happiest.minds.sftpapplication.enums.Constants.*;

@Service
@Slf4j
public class OrganizationService {


    @Autowired
    UserManagementBaseURL userManagementBaseURL;

    @Autowired
    RedisUtility redisUtility;

    public Organization createOrganization(OrganizationDTO organizationDTO) {
        WebClient webClient = WebClient.create(userManagementBaseURL.getOrganizationURL());
        return webClient.post()
                .headers(httpHeaders -> httpHeaders.setBearerAuth(redisUtility.getAdminToken()))
                .body(Mono.just(organizationDTO), Organization.class)
                .retrieve()
                .bodyToMono(Organization.class).block();
    }

    public Organization getOrganizationById(Integer orgId) {
        WebClient webClient = WebClient.create(userManagementBaseURL.getOrganizationURL());
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(ID_PLACE_HOLDER.getValue())
                        .build(orgId))
                .headers(httpHeaders -> httpHeaders.setBearerAuth(redisUtility.getAdminToken()))
                .retrieve()
                .bodyToMono(Organization.class).block();
    }

    public List<Organization> getOrganizations() {
        WebClient webClient = WebClient.create(userManagementBaseURL.getOrganizationURL());
        return webClient.get()
                .headers(httpHeaders -> httpHeaders.setBearerAuth(redisUtility.getAdminToken()))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Organization>>() {
                })
                .block();
    }

    public Organization updateOrganizationById(Integer orgId, OrganizationDTO organizationDTO) {
        WebClient webClient = WebClient.create(userManagementBaseURL.getOrganizationURL());
        return webClient.put()
                .uri(uriBuilder -> uriBuilder
                        .path(ID_PLACE_HOLDER.getValue())
                        .build(orgId))
                .headers(httpHeaders -> httpHeaders.setBearerAuth(redisUtility.getAdminToken()))
                .body(Mono.just(organizationDTO), Organization.class)
                .retrieve()
                .bodyToMono(Organization.class).block();
    }

    public ResponseEntity<HttpStatus> deleteOrganizationById(Integer orgId) {
        WebClient webClient = WebClient.create(userManagementBaseURL.getOrganizationURL());
        ResponseEntity<HttpStatus> response = webClient.delete()
                .uri(uriBuilder -> uriBuilder
                        .path(ID_PLACE_HOLDER.getValue())
                        .build(orgId))
                .headers(httpHeaders -> httpHeaders.setBearerAuth(redisUtility.getAdminToken()))
                .retrieve().toEntity(HttpStatus.class).block();
        log.info("deleteOrganizationById:: status code : {}", response.getStatusCode());
        return response;
    }

}
