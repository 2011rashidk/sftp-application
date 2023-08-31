package com.happiest.minds.sftpapplication.controller;

import com.happiest.minds.sftpapplication.request.RoleDTO;
import com.happiest.minds.sftpapplication.response.Role;
import com.happiest.minds.sftpapplication.service.RoleService;
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
@RequestMapping("api/sftp/application/role")
@Slf4j
public class RoleController {
    @Autowired
    public RoleService roleService;

    @PostMapping
    public ResponseEntity<Role> createRole(@RequestBody RoleDTO roleDTO) {
        log.info("roleDTO: {}", roleDTO);
        Role role = roleService.createRole(roleDTO);
        log.info("role: {}", role);
        return new ResponseEntity<>(role, HttpStatus.CREATED);
    }

    @GetMapping("{roleId}")
    public ResponseEntity<Role> getRoleById(@Valid @NonNull @PathVariable Integer roleId) {
        log.info("roleId: {}", roleId);
        Role role = roleService.getRoleById(roleId);
        log.info("role: {}", role);
        return new ResponseEntity<>(role, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Role>> getAllRoles() {
        List<Role> roles = roleService.getAllRoles();
        log.info("roles: {}", roles);
        return new ResponseEntity<>(roles, HttpStatus.OK);

    }

    @PutMapping("{roleId}")
    public ResponseEntity<Role> updateRoleById(@Valid @NonNull @PathVariable Integer roleId,
                                               @Valid @RequestBody RoleDTO roleDTO) {
        log.info("Input request:: roleId: {}, roleDTO: {}", roleId, roleDTO);
        Role role = roleService.updateRoleById(roleId, roleDTO);
        log.info("role: {}", role);
        if (role != null) {
            return new ResponseEntity<>(role, HttpStatus.OK);
        }
        log.error(NO_DATA_FOUND.getValue().concat(role.getRoleId().toString()));
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("{roleId}")
    public ResponseEntity<HttpStatus> deleteRole(@Valid @NonNull @PathVariable Integer roleId) {
        log.info("roleId: {}", roleId);
        return roleService.deleteRoleById(roleId);
    }

}
