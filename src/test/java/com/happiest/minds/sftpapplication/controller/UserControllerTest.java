package com.happiest.minds.sftpapplication.controller;

import com.happiest.minds.sftpapplication.request.UserDTO;
import com.happiest.minds.sftpapplication.response.User;
import com.happiest.minds.sftpapplication.service.UserService;
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

class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateUserSuccess() {
        UserDTO userDTO = new UserDTO();

        User user = new User();
        when(userService.createUser(userDTO)).thenReturn(user);

        ResponseEntity<User> response = userController.createUser(userDTO);

        verify(userService).createUser(userDTO);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(user, response.getBody());
    }

    @Test
    void testGetUserByIdSuccess() {
        Integer userId = 1;
        User user = new User();
        when(userService.getUserById(userId)).thenReturn(user);
        ResponseEntity<User> response = userController.getUserById(userId);
        verify(userService).getUserById(userId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
    }

    @Test
    void testGetAllUsersSuccess() {
        List<User> userList = new ArrayList<>();
        when(userService.getAllUsers()).thenReturn(userList);
        ResponseEntity<List<User>> response = userController.getAllUsers();
        verify(userService).getAllUsers();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userList, response.getBody());
    }

    @Test
    void testUpdateUserByIdSuccess() {
        Integer userId = 1;
        UserDTO userDTO = new UserDTO();
        User updatedUser = new User();
        when(userService.updateUserById(userId, userDTO)).thenReturn(updatedUser);

        ResponseEntity<User> response = userController.updateUserById(userId, userDTO);

        verify(userService).updateUserById(userId, userDTO);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedUser, response.getBody());
    }

    @Test
    void testDeleteUserByIdSuccess() {
        Integer userId = 1;

        when(userService.deleteUserById(userId)).thenReturn(new ResponseEntity<>(HttpStatus.NO_CONTENT));

        ResponseEntity<HttpStatus> response = userController.deleteUserById(userId);

        verify(userService).deleteUserById(userId);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}