package com.happiest.minds.sftpapplication.service;

import com.happiest.minds.sftpapplication.request.PermissionDTO;
import com.happiest.minds.sftpapplication.response.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
public class PermissionService {


    public ResponseEntity<Response> createUser(PermissionDTO permissionDTO, BindingResult bindingResult) {
        return null;
    }

    public ResponseEntity<Response> createPermission(PermissionDTO permissionDTO, BindingResult bindingResult) {
        return null;
    }

    public ResponseEntity<Response> getPermissions() {
        return null;
    }

    public ResponseEntity<Response> getPermission(Integer permissionId) {
        return null;
    }

    public ResponseEntity<Response> updatePermission(PermissionDTO permissionDTO, BindingResult bindingResult) {
        return null;
    }

    public ResponseEntity<Response> deletePermission(Integer permissionID) {
        return null;
    }
}
