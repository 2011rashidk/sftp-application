package com.happiest.minds.sftpapplication.service;

import com.happiest.minds.sftpapplication.request.RoleDTO;
import com.happiest.minds.sftpapplication.response.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
public class RoleService {
    public ResponseEntity<Response> createRole(RoleDTO roleDTO, BindingResult bindingResult) {
        return null;
    }

    public ResponseEntity<Response> getRoles() {
        return null;
    }

    public ResponseEntity<Response> getRole(Integer roleId) {
        return null;
    }

    public ResponseEntity<Response> updateRole(RoleDTO roleDTO, BindingResult bindingResult) {
        return null;
    }

    public ResponseEntity<Response> deleteRole(Integer roleId) {
        return null;
    }
}
