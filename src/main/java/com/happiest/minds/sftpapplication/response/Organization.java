package com.happiest.minds.sftpapplication.response;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Organization {

    private Integer orgId;
    private String name;
    private String location;

}
