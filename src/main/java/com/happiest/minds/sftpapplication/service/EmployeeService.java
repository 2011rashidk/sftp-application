package com.happiest.minds.sftpapplication.service;

import com.happiest.minds.sftpapplication.config.UserManagementBaseURL;
import com.happiest.minds.sftpapplication.dto.EmployeeDTO;
import com.happiest.minds.sftpapplication.response.Employee;
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
public class EmployeeService {

    @Autowired
    UserManagementBaseURL userManagementBaseURL;

    @Autowired
    RedisUtility redisUtility;

    public Employee createEmployee(EmployeeDTO employeeDTO) {
        WebClient webClient = WebClient.create(userManagementBaseURL.getEmployeeURL());
        return webClient.post()
                .headers(httpHeaders -> httpHeaders.setBearerAuth(redisUtility.getAdminToken()))
                .body(Mono.just(employeeDTO), Employee.class)
                .retrieve()
                .bodyToMono(Employee.class).block();
    }

    public List<Employee> createEmployees(List<EmployeeDTO> employeeDTOS) {
        WebClient webClient = WebClient.create(userManagementBaseURL.getEmployeeURL());
        return webClient.post()
                .uri(EMPLOYEES.getValue())
                .headers(httpHeaders -> httpHeaders.setBearerAuth(redisUtility.getAdminToken()))
                .body(Mono.just(employeeDTOS), Employee.class)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Employee>>() {
                })
                .block();
    }

    public Employee getEmployeeById(String id) {
        WebClient webClient = WebClient.create(userManagementBaseURL.getEmployeeURL());
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(ID_PLACE_HOLDER.getValue())
                        .build(id))
                .headers(httpHeaders -> httpHeaders.setBearerAuth(redisUtility.getAdminToken()))
                .retrieve()
                .bodyToMono(Employee.class).block();
    }

    public List<Employee> getEmployees() {
        WebClient webClient = WebClient.create(userManagementBaseURL.getEmployeeURL());
        return webClient.get()
                .headers(httpHeaders -> httpHeaders.setBearerAuth(redisUtility.getAdminToken()))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Employee>>() {
                })
                .block();
    }

    public Employee updateEmployeeById(EmployeeDTO employeeDTO) {
        WebClient webClient = WebClient.create(userManagementBaseURL.getEmployeeURL());
        return webClient.put()
                .headers(httpHeaders -> httpHeaders.setBearerAuth(redisUtility.getAdminToken()))
                .body(Mono.just(employeeDTO), Employee.class)
                .retrieve()
                .bodyToMono(Employee.class).block();
    }

    public ResponseEntity<HttpStatus> deleteEmployeeById(String id) {
        WebClient webClient = WebClient.create(userManagementBaseURL.getEmployeeURL());
        ResponseEntity<HttpStatus> response = webClient.delete()
                .uri(uriBuilder -> uriBuilder
                        .path(ID_PLACE_HOLDER.getValue())
                        .build(id))
                .headers(httpHeaders -> httpHeaders.setBearerAuth(redisUtility.getAdminToken()))
                .retrieve().toEntity(HttpStatus.class).block();
        log.info("deleteEmployeeById:: status code : {}", response.getStatusCode());
        return response;
    }

}
