package com.happiest.minds.sftpapplication.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class PermissionDTO {

    @NotEmpty
    private String permissionName;
}
