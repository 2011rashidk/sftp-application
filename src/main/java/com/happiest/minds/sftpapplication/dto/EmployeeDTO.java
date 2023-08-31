package com.happiest.minds.sftpapplication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmployeeDTO {

    private String id;
    private String name;
    private Integer age;
    private String email;
    private String mobile;
    private String location;
    private Integer orgId;
    private String department;

}
