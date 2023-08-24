package com.happiest.minds.sftpapplication.service;

import com.happiest.minds.sftpapplication.request.UserDTO;
import com.happiest.minds.sftpapplication.response.Response;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
public class UserService {
    public ResponseEntity<Response> createUser(@Valid UserDTO userDTO, BindingResult bindingResult) {
        return null;
    }

    public ResponseEntity<Response> getUsers() {
        return null;
    }

    public ResponseEntity<Response> getUser(Integer userId) {
        return null;
    }

    public ResponseEntity<Response> updateUser(UserDTO userDTO, BindingResult bindingResult) {
        return null;
    }

    public ResponseEntity<Response> deleteUser(Integer userId) {
        return null;
    }
}
