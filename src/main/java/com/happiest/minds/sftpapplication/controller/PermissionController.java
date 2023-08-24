package com.happiest.minds.sftpapplication.controller;

import com.happiest.minds.sftpapplication.request.PermissionDTO;
import com.happiest.minds.sftpapplication.response.Response;
import com.happiest.minds.sftpapplication.service.PermissionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/sftp/application/permission")
public class PermissionController {

    @Autowired
    PermissionService permissionService;

    @PostMapping
    public ResponseEntity<Response> createPermission(@Valid @RequestBody PermissionDTO permissionDTO, BindingResult bindingResult) {
        return permissionService.createPermission(permissionDTO, bindingResult);
    }

    @GetMapping
    public ResponseEntity<Response> getPermissions() {
        return permissionService.getPermissions();
    }

    @GetMapping("{userId}")
    public ResponseEntity<Response> getPermission(@PathVariable Integer permissionId) {
        return permissionService.getPermission(permissionId);
    }

    @PutMapping("{userId}")
    public ResponseEntity<Response> updatePermission(@Valid PermissionDTO permissionDTO, BindingResult bindingResult) {
        return permissionService.updatePermission(permissionDTO, bindingResult);
    }

    @DeleteMapping("{userId}")
    public ResponseEntity<Response> deletePermission(@PathVariable Integer permissionID) {
        return permissionService.deletePermission(permissionID);
    }
}
