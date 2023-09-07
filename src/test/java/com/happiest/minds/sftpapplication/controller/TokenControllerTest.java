package com.happiest.minds.sftpapplication.controller;

import com.happiest.minds.sftpapplication.request.TokenDTO;
import com.happiest.minds.sftpapplication.response.TokenResponse;
import com.happiest.minds.sftpapplication.service.TokenService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class TokenControllerTest {

    @Mock
    private TokenService tokenService;

    @InjectMocks
    private TokenController tokenController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetTokenSuccess() {
        TokenDTO tokenDTO = new TokenDTO();
        TokenResponse expectedTokenResponse = new TokenResponse();
        when(tokenService.getToken(any(TokenDTO.class))).thenReturn(expectedTokenResponse);
        ResponseEntity<TokenResponse> response = tokenController.getToken(tokenDTO);
        verify(tokenService).getToken(tokenDTO);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedTokenResponse, response.getBody());
    }

}