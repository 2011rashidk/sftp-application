package com.happiest.minds.sftpapplication.controller;

import com.happiest.minds.sftpapplication.request.PermissionDTO;
import com.happiest.minds.sftpapplication.response.Permission;
import com.happiest.minds.sftpapplication.service.PermissionService;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.happiest.minds.sftpapplication.enums.Constants.*;

@RestController
@RequestMapping("api/sftp/application/permission")
@Slf4j
public class PermissionController {
    @Autowired
    public PermissionService permissionService;

    @PostMapping
    public ResponseEntity<Permission> createPermission(@Valid @RequestBody PermissionDTO permissionDTO) {
        log.info(PERMISSION_DTO.getValue(), permissionDTO);
        Permission permission = permissionService.createPermission(permissionDTO);
        log.info(PERMISSION.getValue(), permission);
        return new ResponseEntity<>(permission, HttpStatus.CREATED);
    }

    @GetMapping("{permissionId}")
    public ResponseEntity<Permission> getPermissionById(@Valid @NonNull @PathVariable Integer permissionId) {
        log.info(ID.getValue(), permissionId);
        Permission permission = permissionService.getPermissionById(permissionId);
        log.info(PERMISSION.getValue(), permission);
        return new ResponseEntity<>(permission, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Permission>> getAllPermissions() {
        List<Permission> permissions = permissionService.getAllPermissions();
        log.info(PERMISSION.getValue(), permissions);
        return new ResponseEntity<>(permissions, HttpStatus.OK);
    }

    @PutMapping("{permissionId}")
    public ResponseEntity<Permission> updatePermissionById(@Valid @NonNull @PathVariable Integer permissionId,
                                                           @Valid @RequestBody PermissionDTO permissionDTO) {
        log.info(ID.getValue(), permissionId);
        log.info(PERMISSION_DTO.getValue(), permissionDTO);
        Permission permission = permissionService.updatePermissionById(permissionId, permissionDTO);
        log.info(PERMISSION.getValue(), permission);
        if (permission != null) {
            return new ResponseEntity<>(permission, HttpStatus.OK);
        }
        log.error(NO_DATA_FOUND.getValue().concat(permission.getPermissionId().toString()));
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("{permissionId}")
    public ResponseEntity<HttpStatus> deletePermissionById(@Valid @NonNull @PathVariable Integer permissionId) {
        log.info(ID.getValue(), permissionId);
        return permissionService.deletePermissionById(permissionId);
    }

}
