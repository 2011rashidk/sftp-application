package com.happiest.minds.sftpapplication.controller;

import com.happiest.minds.sftpapplication.response.Response;
import com.happiest.minds.sftpapplication.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/sftp/application/auth")
public class AuthController {

    @Autowired
    AuthService authService;


    @GetMapping("token")
    public ResponseEntity<Response> authenticateUser(@PathVariable String username, String password) {
        return authService.authenticateUser();
    }


}
