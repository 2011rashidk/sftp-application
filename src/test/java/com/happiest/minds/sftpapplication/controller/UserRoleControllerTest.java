package com.happiest.minds.sftpapplication.controller;

import com.happiest.minds.sftpapplication.request.UserRoleDTO;
import com.happiest.minds.sftpapplication.response.UserRole;
import com.happiest.minds.sftpapplication.service.UserRoleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserRoleControllerTest {

    @Mock
    private UserRoleService userRoleService;

    @InjectMocks
    private UserRoleController userRoleController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddRoleToUserSuccess() {
        UserRoleDTO userRoleDTO = new UserRoleDTO();

        UserRole userRole = new UserRole();

        when(userRoleService.addRoleToUser(userRoleDTO)).thenReturn(userRole);

        ResponseEntity<UserRole> response = userRoleController.addRoleToUser(userRoleDTO);

        verify(userRoleService).addRoleToUser(userRoleDTO);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(userRole, response.getBody());
    }

    @Test
    void testDeleteRoleOfUserSuccess() {
        UserRoleDTO userRoleDTO = new UserRoleDTO();
        when(userRoleService.deleteByUserIdAndRoleId(userRoleDTO)).thenReturn(null);
        ResponseEntity<HttpStatus> response = userRoleController.deleteRoleOfUser(userRoleDTO);
        verify(userRoleService).deleteByUserIdAndRoleId(userRoleDTO);
    }

}