package com.happiest.minds.sftpapplication.response;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Employee {

    private String id;
    private String name;
    private Integer age;
    private String email;
    private String mobile;
    private String location;
    private Integer orgId;
    private String department;

}
