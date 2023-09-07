package com.happiest.minds.sftpapplication.controller;

import com.happiest.minds.sftpapplication.request.PermissionDTO;
import com.happiest.minds.sftpapplication.response.Permission;
import com.happiest.minds.sftpapplication.service.PermissionService;
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

class PermissionControllerTest {
    @Mock
    private PermissionService permissionService;
    @InjectMocks
    private PermissionController permissionController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreatePermission() {
        PermissionDTO permissionDTO = new PermissionDTO();
        Permission permission = new Permission();
        when(permissionService.createPermission(any(PermissionDTO.class))).thenReturn(permission);
        ResponseEntity<Permission> response = permissionController.createPermission(permissionDTO);
        verify(permissionService).createPermission(permissionDTO);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(permission, response.getBody());
    }

    @Test
    void testGetPermissionById() {
        Integer permissionId = 1;
        Permission permission = new Permission();
        when(permissionService.getPermissionById(permissionId)).thenReturn(permission);
        ResponseEntity<Permission> response = permissionController.getPermissionById(permissionId);
        verify(permissionService).getPermissionById(permissionId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(permission, response.getBody());
    }

    @Test
    void testGetAllPermissions() {
        List<Permission> permissions = new ArrayList<>();

        when(permissionService.getAllPermissions()).thenReturn(permissions);

        ResponseEntity<List<Permission>> response = permissionController.getAllPermissions();

        verify(permissionService).getAllPermissions();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(permissions, response.getBody());
    }

    @Test
    void testUpdatePermissionById() {
        Integer permissionId = 1;
        PermissionDTO permissionDTO = new PermissionDTO();
        Permission updatedPermission = new Permission();

        when(permissionService.updatePermissionById(permissionId, permissionDTO)).thenReturn(updatedPermission);
        ResponseEntity<Permission> response = permissionController.updatePermissionById(permissionId, permissionDTO);
        verify(permissionService).updatePermissionById(permissionId, permissionDTO);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedPermission, response.getBody());
    }

    /*@Test
    void testUpdatePermissionByIdNotFound() {
        Integer permissionId = 1;
        PermissionDTO permissionDTO = new PermissionDTO();

        when(permissionService.updatePermissionById(permissionId, permissionDTO)).thenReturn(null);
        ResponseEntity<Permission> response = permissionController.updatePermissionById(permissionId, permissionDTO);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }*/

    @Test
    void testDeletePermissionById() {
        Integer permissionId = 1;

        when(permissionService.deletePermissionById(permissionId)).thenReturn(new ResponseEntity<>(HttpStatus.NO_CONTENT));

        ResponseEntity<HttpStatus> response = permissionController.deletePermissionById(permissionId);

        verify(permissionService).deletePermissionById(permissionId);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

}