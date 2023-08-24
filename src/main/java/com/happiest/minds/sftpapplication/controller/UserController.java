package com.happiest.minds.sftpapplication.controller;

import com.happiest.minds.sftpapplication.request.UserDTO;
import com.happiest.minds.sftpapplication.response.Response;
import com.happiest.minds.sftpapplication.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/sftp/application/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<Response> createUser(@Valid @RequestBody UserDTO userDTO, BindingResult bindingResult) {
        return userService.createUser(userDTO, bindingResult);
    }

    @GetMapping
    public ResponseEntity<Response> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("{userId}")
    public ResponseEntity<Response> getUser(@PathVariable Integer userId) {
        return userService.getUser(userId);
    }

    @PutMapping("{userId}")
    public ResponseEntity<Response> updateUser(@Valid UserDTO userDTO, BindingResult bindingResult) {
        return userService.updateUser(userDTO, bindingResult);
    }

    @DeleteMapping("{userId}")
    public ResponseEntity<Response> deleteUser(@PathVariable Integer userId) {
        return userService.deleteUser(userId);
    }

}
