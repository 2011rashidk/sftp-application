package com.happiest.minds.sftpapplication.controller;

import com.happiest.minds.sftpapplication.request.UserDTO;
import com.happiest.minds.sftpapplication.response.User;
import com.happiest.minds.sftpapplication.service.UserService;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.happiest.minds.sftpapplication.enums.Constants.*;


@RestController
@RequestMapping("api/sftp/application/user")
@Slf4j
public class UserController {

    @Autowired
    public UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody UserDTO userDTO) {
        log.info(USER_DTO.getValue(), userDTO);
        User user = userService.createUser(userDTO);
        log.info(USER.getValue(), user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping("{userId}")
    public ResponseEntity<User> getUserById(@Valid @NonNull @PathVariable Integer userId) {
        log.info(ID.getValue(), userId);
        User user = userService.getUserById(userId);
        log.info(USER.getValue(), user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        log.info(USER.getValue(), users);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PutMapping("{userId}")
    public ResponseEntity<User> updateUserById(@Valid @NonNull @PathVariable Integer userId,
                                               @Valid @RequestBody UserDTO userDTO) {
        log.info(ID.getValue(), userId);
        log.info(USER_DTO.getValue(), userDTO);
        User user = userService.updateUserById(userId, userDTO);
        log.info(USER.getValue(), user);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        log.error(NO_DATA_FOUND.getValue().concat(userId.toString()));
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("{userId}")
    public ResponseEntity<HttpStatus> deleteUserById(@Valid @NonNull @PathVariable Integer userId) {
        log.info(ID.getValue(), userId);
        return userService.deleteUserById(userId);
    }

}
	

