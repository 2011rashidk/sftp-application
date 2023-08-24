package com.happiest.minds.sftpapplication.service;

import com.happiest.minds.sftpapplication.request.RoleDTO;
import com.happiest.minds.sftpapplication.response.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
public class RoleService {
    public ResponseEntity<Response> createRole(RoleDTO roleDTO, BindingResult bindingResult) {

    }

    public ResponseEntity<Response> getRoles() {

    }

    public ResponseEntity<Response> getRole(Integer roleId) {

    }

    public ResponseEntity<Response> updateRole(RoleDTO roleDTO, BindingResult bindingResult) {

    }

    public ResponseEntity<Response> deleteRole(Integer roleId) {

    }
}
