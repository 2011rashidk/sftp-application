package com.happiest.minds.sftpapplication.controller;

import com.happiest.minds.sftpapplication.response.Response;
import com.happiest.minds.sftpapplication.service.SftpService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/sftp/application/server")
@Slf4j
public class SftpController {

    @Autowired
    SftpService sftpService;

    @GetMapping("listOfFiles")
    public ResponseEntity<Response> getListOfFiles(@Valid @RequestParam @NotEmpty String location) {
        log.info("Location: {}", location);
        return sftpService.getListOfFiles(location);
    }

    @PostMapping("readFile")
    public ResponseEntity<Response> readFile(@Valid @RequestParam @NotEmpty String location, @NotEmpty String filename) {
        log.info("Location: {}, Filename: {}", location, filename);
        return sftpService.readFile(location, filename);
    }

    @PostMapping("loadFile")
    public ResponseEntity<Response> loadFile(@Valid @RequestParam @NotEmpty String location, @NotEmpty String filename) {
        log.info("Location: {}, Filename: {}", location, filename);
        return sftpService.loadFile(location, filename);
    }

}
