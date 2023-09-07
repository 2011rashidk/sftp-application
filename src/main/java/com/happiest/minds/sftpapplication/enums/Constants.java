package com.happiest.minds.sftpapplication.enums;

public enum Constants {

    LIST_OF_FILES_RETRIEVED("list of files retrieved successfully"),
    FILE_EMPTY("File is null or empty"),
    EMPLOYEES("/employees"),
    ID_PLACE_HOLDER("/{id}"),
    NO_DATA_FOUND("Not found data with id: "),
    RECORD_DELETED_FOR_ID("Record deleted for id: "),
    ADMIN("admin"),
    EXCEPTION("Exception: {}"),
    FILES_CONTENT_RETRIEVED("Files contents retrieved successfully"),
    FILES_CONTENT_SAVED("Files contents saved to database successfully"),
    ID_NOT_FOUND("ID not found"),
    USERNAME_NOT_FOUND("Username not found!"),
    ID("Id: {}"),
    EMPLOYEE_DTO("employeeDTO: {}"),
    EMPLOYEE("employee:{}"),
    TOKEN_DTO("tokenDTO: {}"),
    ORGANIZATION_DTO("organizationDTO: {}"),
    ORGANIZATION("organization: {}"),
    PERMISSION_DTO("permissionDTO: {}"),
    PERMISSION("permission: {}"),
    ROLE_DTO("roleDTO: {}"),
    ROLE("role: {}"),
    ROLE_PERMISSION_DTO("rolePermissionDTO: {}"),
    ROLE_PERMISSION("rolePermission: {}"),
    LOCATION("Location: {}"),
    LOCATION_FILENAME("Location: {}, Filename: {}"),
    USER_DTO("userDTO: {}"),
    USER("user: {}"),
    USER_ROLE_DTO("userRoleDTO: {}"),
    USER_ROLE("userRole: {}"),
    ACCESS_DENIED("Access denied");

    private final String value;

    Constants(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
