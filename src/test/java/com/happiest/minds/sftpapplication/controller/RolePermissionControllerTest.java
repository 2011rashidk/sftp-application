package com.happiest.minds.sftpapplication.controller;

import com.happiest.minds.sftpapplication.request.RolePermissionDTO;
import com.happiest.minds.sftpapplication.response.RolePermission;
import com.happiest.minds.sftpapplication.service.RolePermissionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class RolePermissionControllerTest {

    @Mock
    private RolePermissionService rolePermissionService;

    @InjectMocks
    private RolePermissionController rolePermissionController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddPermissionToRoleSuccess() {
        RolePermissionDTO rolePermissionDTO = new RolePermissionDTO();

        RolePermission rolePermission = new RolePermission();
        when(rolePermissionService.addPermissionToRole(any(RolePermissionDTO.class))).thenReturn(rolePermission);

        ResponseEntity<RolePermission> response = rolePermissionController.addPermissionToRole(rolePermissionDTO);

        verify(rolePermissionService).addPermissionToRole(rolePermissionDTO);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(rolePermission, response.getBody());
    }

    @Test
    void testDeletePermissionOfRoleSuccess() {
        RolePermissionDTO rolePermissionDTO = new RolePermissionDTO();
        when(rolePermissionService.deleteByRoleIdAndPermissionId(any(RolePermissionDTO.class))).thenReturn(null);
        ResponseEntity<HttpStatus> response = rolePermissionController.deletePermissionOfRole(rolePermissionDTO);
        verify(rolePermissionService).deleteByRoleIdAndPermissionId(rolePermissionDTO);
    }
}