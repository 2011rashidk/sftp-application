package com.happiest.minds.sftpapplication.response;

import lombok.Data;
import lombok.ToString;

import java.util.Set;

@Data
@ToString
public class Role {

    private Integer roleId;
    private String roleName;
    private Set<Permission> permissions;

}
