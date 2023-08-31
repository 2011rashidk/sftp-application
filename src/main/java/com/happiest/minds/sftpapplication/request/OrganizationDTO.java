package com.happiest.minds.sftpapplication.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class OrganizationDTO {

    @NotEmpty
    private String name;

    @NotEmpty
    private String location;
}
