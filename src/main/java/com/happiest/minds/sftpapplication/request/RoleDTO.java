package com.happiest.minds.sftpapplication.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class RoleDTO {

    @NotEmpty
    private String roleName;

    private Set<PermissionDTO> permissions = new HashSet<>();
}
