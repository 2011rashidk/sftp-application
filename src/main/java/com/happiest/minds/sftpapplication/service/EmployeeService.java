package com.happiest.minds.sftpapplication.service;

import com.happiest.minds.sftpapplication.config.UserManagementBaseURL;
import com.happiest.minds.sftpapplication.dto.EmployeeDTO;
import com.happiest.minds.sftpapplication.exception.AccessDeniedException;
import com.happiest.minds.sftpapplication.exception.BadRequestException;
import com.happiest.minds.sftpapplication.exception.DataNotFoundException;
import com.happiest.minds.sftpapplication.response.Employee;
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
public class EmployeeService {

    @Autowired
    UserManagementBaseURL userManagementBaseURL;

    public Employee createEmployee(EmployeeDTO employeeDTO, String jwtToken) {
        WebClient webClient = WebClient.create(userManagementBaseURL.getEmployeeURL());
        return webClient.post()
                .headers(httpHeaders -> httpHeaders.setBearerAuth(jwtToken.substring(7)))
                .body(Mono.just(employeeDTO), Employee.class)
                .retrieve()
                .bodyToMono(Employee.class)
                .onErrorResume(WebClientResponseException.class, e -> {
                    log.error(e.getResponseBodyAsString());
                    if (e.getStatusCode() == HttpStatus.FORBIDDEN) {
                        throw new AccessDeniedException(ACCESS_DENIED.getValue());
                    } else if (e.getStatusCode() == HttpStatus.BAD_REQUEST) {
                        throw new BadRequestException(e.getResponseBodyAsString());
                    } else {
                        return Mono.error(new Exception(e.getMessage()));
                    }
                })
                .block();
    }

    public List<Employee> createEmployees(List<EmployeeDTO> employeeDTOS, String jwtToken) {
        WebClient webClient = WebClient.create(userManagementBaseURL.getEmployeeURL());
        return webClient.post()
                .uri(EMPLOYEES.getValue())
                .headers(httpHeaders -> httpHeaders.setBearerAuth(jwtToken.substring(7)))
                .body(Mono.just(employeeDTOS), Employee.class)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Employee>>() {
                })
                .onErrorResume(WebClientResponseException.class, e -> {
                    log.error(e.getResponseBodyAsString());
                    if (e.getStatusCode() == HttpStatus.FORBIDDEN) {
                        throw new AccessDeniedException(ACCESS_DENIED.getValue());
                    } else if (e.getStatusCode() == HttpStatus.BAD_REQUEST) {
                        throw new BadRequestException(e.getResponseBodyAsString());
                    } else {
                        return Mono.error(new Exception(e.getMessage()));
                    }
                })
                .block();
    }

    public Employee getEmployeeById(String id, String jwtToken) {
        WebClient webClient = WebClient.create(userManagementBaseURL.getEmployeeURL());
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(ID_PLACE_HOLDER.getValue())
                        .build(id))
                .headers(httpHeaders -> httpHeaders.setBearerAuth(jwtToken.substring(7)))
                .retrieve()
                .bodyToMono(Employee.class)
                .onErrorResume(WebClientResponseException.class, e -> {
                    log.error(e.getResponseBodyAsString());
                    if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                        return Mono.error(new DataNotFoundException(ID_NOT_FOUND.getValue()));
                    } else if (e.getStatusCode() == HttpStatus.FORBIDDEN) {
                        throw new AccessDeniedException(ACCESS_DENIED.getValue());
                    } else if (e.getStatusCode() == HttpStatus.BAD_REQUEST) {
                        throw new BadRequestException(e.getResponseBodyAsString());
                    } else {
                        return Mono.error(new Exception(e.getMessage()));
                    }
                })
                .block();
    }

    public List<Employee> getEmployees(String jwtToken) {
        WebClient webClient = WebClient.create(userManagementBaseURL.getEmployeeURL());
        return webClient.get()
                .headers(httpHeaders -> httpHeaders.setBearerAuth(jwtToken.substring(7)))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Employee>>() {
                })
                .onErrorResume(WebClientResponseException.class, e -> {
                    log.error(e.getResponseBodyAsString());
                    if (e.getStatusCode() == HttpStatus.FORBIDDEN) {
                        throw new AccessDeniedException(ACCESS_DENIED.getValue());
                    } else if (e.getStatusCode() == HttpStatus.BAD_REQUEST) {
                        throw new BadRequestException(e.getResponseBodyAsString());
                    } else {
                        return Mono.error(new Exception(e.getMessage()));
                    }
                })
                .block();
    }

    public Employee updateEmployeeById(EmployeeDTO employeeDTO, String jwtToken) {
        WebClient webClient = WebClient.create(userManagementBaseURL.getEmployeeURL());
        return webClient.put()
                .headers(httpHeaders -> httpHeaders.setBearerAuth(jwtToken.substring(7)))
                .body(Mono.just(employeeDTO), Employee.class)
                .retrieve()
                .bodyToMono(Employee.class)
                .onErrorResume(WebClientResponseException.class, e -> {
                    log.error(e.getResponseBodyAsString());
                    if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                        return Mono.error(new DataNotFoundException(ID_NOT_FOUND.getValue()));
                    } else if (e.getStatusCode() == HttpStatus.FORBIDDEN) {
                        throw new AccessDeniedException(ACCESS_DENIED.getValue());
                    } else if (e.getStatusCode() == HttpStatus.BAD_REQUEST) {
                        throw new BadRequestException(e.getResponseBodyAsString());
                    } else {
                        return Mono.error(new Exception(e.getMessage()));
                    }
                })
                .block();
    }

    public ResponseEntity<HttpStatus> deleteEmployeeById(String id, String jwtToken) {
        WebClient webClient = WebClient.create(userManagementBaseURL.getEmployeeURL());
        ResponseEntity<HttpStatus> response = webClient.delete()
                .uri(uriBuilder -> uriBuilder
                        .path(ID_PLACE_HOLDER.getValue())
                        .build(id))
                .headers(httpHeaders -> httpHeaders.setBearerAuth(jwtToken.substring(7)))
                .retrieve().toEntity(HttpStatus.class)
                .onErrorResume(WebClientResponseException.class, e -> {
                    log.error(e.getResponseBodyAsString());
                    if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                        return Mono.error(new DataNotFoundException(ID_NOT_FOUND.getValue()));
                    } else if (e.getStatusCode() == HttpStatus.FORBIDDEN) {
                        throw new AccessDeniedException(ACCESS_DENIED.getValue());
                    } else if (e.getStatusCode() == HttpStatus.BAD_REQUEST) {
                        throw new BadRequestException(e.getResponseBodyAsString());
                    } else {
                        return Mono.error(new Exception(e.getMessage()));
                    }
                })
                .block();
        log.info(response.getStatusCode().toString());
        return response;
    }

}
