package com.happiest.minds.sftpapplication.request;

import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NonNull;

@Data
public class RolePermissionDTO {

    @NonNull
    @Positive
    private Integer roleId;

    @NonNull
    @Positive
    private Integer permissionId;
}
