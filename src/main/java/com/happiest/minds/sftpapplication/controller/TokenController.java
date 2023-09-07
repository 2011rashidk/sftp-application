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

import static com.happiest.minds.sftpapplication.enums.Constants.*;

@RestController
@RequestMapping("api/sftp/application/login")
@Slf4j
public class TokenController {

    @Autowired
    TokenService tokenService;

    @PostMapping
    public ResponseEntity<TokenResponse> getToken(@Valid @RequestBody TokenDTO tokenDTO) {
        log.info(TOKEN_DTO.getValue(), tokenDTO);
        TokenResponse tokenResponse = tokenService.getToken(tokenDTO);
        return new ResponseEntity<>(tokenResponse, HttpStatus.OK);
    }

}