package com.happiest.minds.sftpapplication.controller;

import com.happiest.minds.sftpapplication.response.Response;
import com.happiest.minds.sftpapplication.service.SftpService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class SftpControllerTest {

    @Mock
    private SftpService sftpService;

    @InjectMocks
    private SftpController sftpController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetListOfFilesSuccess() {
        String location = "/some/location";
        Response expectedResponse = new Response();
        when(sftpService.getListOfFiles(location)).thenReturn(new ResponseEntity<>(expectedResponse, HttpStatus.OK));
        ResponseEntity<Response> response = sftpController.getListOfFiles(location);
        verify(sftpService).getListOfFiles(location);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResponse, response.getBody());
    }

    @Test
    void testReadFileSuccess() {
        String location = "/some/location";
        String filename = "example.txt";
        Response expectedResponse = new Response();
        when(sftpService.readFile(location, filename)).thenReturn(new ResponseEntity<>(expectedResponse, HttpStatus.OK));
        ResponseEntity<Response> response = sftpController.readFile(location, filename);
        verify(sftpService).readFile(location, filename);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResponse, response.getBody());
    }
    
    @Test
    void testLoadFileSuccess() {
        String location = "/some/location";
        String filename = "example.txt";
        Response expectedResponse = new Response();
        when(sftpService.loadFile(location, filename, "someToken")).thenReturn(new ResponseEntity<>(expectedResponse, HttpStatus.OK));
        ResponseEntity<Response> response = sftpController.loadFile(location, filename, "someToken");
        verify(sftpService).loadFile(location, filename, "someToken");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResponse, response.getBody());
    }

}