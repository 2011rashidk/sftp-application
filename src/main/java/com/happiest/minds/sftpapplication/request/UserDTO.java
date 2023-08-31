package com.happiest.minds.sftpapplication.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.Set;

@Data
public class UserDTO {

    @NotEmpty
    private String name;

    @NotEmpty
    private String email;

    @NotEmpty
    private String mobile;

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

    private Set<RoleDTO> roles;
}
