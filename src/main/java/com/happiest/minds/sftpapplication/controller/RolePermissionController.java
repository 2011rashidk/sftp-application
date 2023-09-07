package com.happiest.minds.sftpapplication.controller;

import com.happiest.minds.sftpapplication.request.RolePermissionDTO;
import com.happiest.minds.sftpapplication.response.RolePermission;
import com.happiest.minds.sftpapplication.service.RolePermissionService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.happiest.minds.sftpapplication.enums.Constants.*;


@RestController
@RequestMapping("api/sftp/application/role-permission")
@Slf4j
public class RolePermissionController {

    @Autowired
    RolePermissionService rolePermissionService;

    @PostMapping
    public ResponseEntity<RolePermission> addPermissionToRole(@Valid @RequestBody RolePermissionDTO rolePermissionDTO) {
        log.info(ROLE_PERMISSION_DTO.getValue(), rolePermissionDTO);
        RolePermission rolePermission = rolePermissionService.addPermissionToRole(rolePermissionDTO);
        log.info(ROLE_PERMISSION.getValue(), rolePermission);
        return new ResponseEntity<>(rolePermission, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> deletePermissionOfRole(@Valid @RequestBody RolePermissionDTO rolePermissionDTO) {
        log.info(ROLE_PERMISSION_DTO.getValue(), rolePermissionDTO);
        return rolePermissionService.deleteByRoleIdAndPermissionId(rolePermissionDTO);
    }

}
