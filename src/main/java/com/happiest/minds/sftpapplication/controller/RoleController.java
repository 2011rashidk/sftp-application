package com.happiest.minds.sftpapplication.controller;

import com.happiest.minds.sftpapplication.request.RoleDTO;
import com.happiest.minds.sftpapplication.response.Response;
import com.happiest.minds.sftpapplication.service.RoleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/sftp/application/role")
public class RoleController {

    @Autowired
    RoleService roleService;

    @PostMapping
    public ResponseEntity<Response> createRole(@Valid @RequestBody RoleDTO roleDTO, BindingResult bindingResult) {
        return roleService.createRole(roleDTO, bindingResult);
    }

    @GetMapping
    public ResponseEntity<Response> getRoles() {
        return roleService.getRoles();
    }

    @GetMapping("{roleId}")
    public ResponseEntity<Response> getRole(@PathVariable Integer roleId) {
        return roleService.getRole(roleId);
    }

    @PutMapping("{roleId}")
    public ResponseEntity<Response> updateRole(@Valid RoleDTO roleDTO, BindingResult bindingResult) {
        return roleService.updateRole(roleDTO, bindingResult);
    }

    @DeleteMapping("{roleId}")
    public ResponseEntity<Response> deleteRole(@PathVariable Integer roleId) {
        return roleService.deleteRole(roleId);
    }
}
