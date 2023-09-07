package com.happiest.minds.sftpapplication.controller;

import com.happiest.minds.sftpapplication.request.RoleDTO;
import com.happiest.minds.sftpapplication.response.Role;
import com.happiest.minds.sftpapplication.response.UserRole;
import com.happiest.minds.sftpapplication.service.RoleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class RoleControllerTest {

    @Mock
    private RoleService roleService;

    @InjectMocks
    private RoleController roleController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateRole() {
        RoleDTO roleDTO = new RoleDTO();
        Role role = new Role();
        when(roleService.createRole(roleDTO)).thenReturn(role);
        ResponseEntity<Role> response = roleController.createRole(roleDTO);
        verify(roleService).createRole(roleDTO);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(role, response.getBody());
    }

    @Test
    void testGetRoleById() {
        Integer roleId = 1;
        Role role = new Role();
        when(roleService.getRoleById(roleId)).thenReturn(role);
        ResponseEntity<Role> response = roleController.getRoleById(roleId);
        verify(roleService).getRoleById(roleId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(role, response.getBody());
    }

    @Test
    void testGetAllRoles() {
        List<Role> roles = new ArrayList<>();
        when(roleService.getAllRoles()).thenReturn(roles);
        ResponseEntity<List<Role>> response = roleController.getAllRoles();
        verify(roleService).getAllRoles();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(roles, response.getBody());
    }

    @Test
    void testUpdateRoleById() {
        Integer roleId = 1;
        RoleDTO roleDTO = new RoleDTO();
        Role updatedRole = new Role();
        when(roleService.updateRoleById(roleId, roleDTO)).thenReturn(updatedRole);
        ResponseEntity<Role> response = roleController.updateRoleById(roleId, roleDTO);
        verify(roleService).updateRoleById(roleId, roleDTO);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedRole, response.getBody());
    }

    /*@Test
    void testUpdateRoleByIdRoleNotFound() {
        Integer roleId = 1;
        RoleDTO roleDTO = new RoleDTO();
        when(roleService.updateRoleById(roleId, roleDTO)).thenReturn(null);
        ResponseEntity<Role> response = roleController.updateRoleById(roleId, roleDTO);
        verify(roleService).updateRoleById(roleId, roleDTO);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }*/

    /*@Test
    void testDeleteRoleRoleDeleted() {
        Integer roleId = 1;

//        when(roleService.deleteRoleById(roleId)).thenReturn(HttpStatus.NO_CONTENT);

        ResponseEntity<HttpStatus> response = roleController.deleteRole(roleId);

        verify(roleService).deleteRoleById(roleId);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }*/

}