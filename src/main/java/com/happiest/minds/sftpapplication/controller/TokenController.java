package com.happiest.minds.sftpapplication.controller;

import com.happiest.minds.sftpapplication.request.TokenDTO;
import com.happiest.minds.sftpapplication.response.TokenResponse;
import com.happiest.minds.sftpapplication.service.TokenService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/sftp/application/login")
@Slf4j
public class TokenController {

    @Autowired
    TokenService tokenService;

    @PostMapping
    public ResponseEntity<TokenResponse> getToken(@Valid @RequestBody TokenDTO tokenDTO) {
        log.info("username: {}", tokenDTO.getUsername());
        TokenResponse tokenResponse = tokenService.getToken(tokenDTO);
        return new ResponseEntity<>(tokenResponse, HttpStatus.OK);
    }

//    @GetMapping("refresh/token")
//    public ResponseEntity<TokenRefreshResponse> refreshToken(HttpServletRequest request) throws Exception {
//        String token = jwtAuthenticationService.refreshToken(request);
//        return new ResponseEntity<>(new TokenRefreshResponse(token), HttpStatus.OK);
//    }

}