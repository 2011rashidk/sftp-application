package com.happiest.minds.sftpapplication.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "user.management.base.url")
@Data
public class UserManagementBaseURL {

    private String loginURL;
    private String userURL;
    private String roleURL;
    private String permissionURL;
    private String userRoleURL;
    private String rolePermissionURL;
    private String employeeURL;
    private String organizationURL;

}
