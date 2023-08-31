package com.happiest.minds.sftpapplication.response;

import lombok.Data;
import lombok.ToString;
import java.util.Set;

@Data
@ToString
public class User {

    private Integer userId;
    private String name;
    private String email;
    private String mobile;
    private String username;
    private Set<Role> roles;

}
