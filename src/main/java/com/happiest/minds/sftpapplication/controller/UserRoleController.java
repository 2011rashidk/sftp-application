package com.happiest.minds.sftpapplication.controller;

import com.happiest.minds.sftpapplication.request.UserRoleDTO;
import com.happiest.minds.sftpapplication.response.UserRole;
import com.happiest.minds.sftpapplication.service.UserRoleService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/sftp/application/user-role")
@Slf4j
public class UserRoleController {

    @Autowired
    UserRoleService userRoleService;

    @PostMapping
    public ResponseEntity<UserRole> addRoleToUser(@Valid @RequestBody UserRoleDTO userRoleDTO) {
        log.info("userRoleDTO: {}", userRoleDTO);
        UserRole userRole = userRoleService.addRoleToUser(userRoleDTO);
        log.info("userRole: {}", userRole);
        return new ResponseEntity<>(userRole, HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteRoleOfUser(@Valid @RequestBody UserRoleDTO userRoleDTO) {
        log.info("userRoleDTO: {}", userRoleDTO);
        return userRoleService.deleteByUserIdAndRoleId(userRoleDTO);
    }

}