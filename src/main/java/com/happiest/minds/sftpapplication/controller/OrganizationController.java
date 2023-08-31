package com.happiest.minds.sftpapplication.controller;

import com.happiest.minds.sftpapplication.request.OrganizationDTO;
import com.happiest.minds.sftpapplication.response.Organization;
import com.happiest.minds.sftpapplication.service.OrganizationService;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.happiest.minds.sftpapplication.enums.Constants.*;

@RestController
@RequestMapping("api/sftp/application/organization")
@Slf4j
public class OrganizationController {

    @Autowired
    OrganizationService organizationService;

    @PostMapping
    public ResponseEntity<Organization> createOrganization(@Valid @RequestBody OrganizationDTO organizationDTO) {
        log.info("organizationDTO: {}", organizationDTO);
        Organization organization = organizationService.createOrganization(organizationDTO);
        log.info("organization: {}", organization);
        return new ResponseEntity<>(organization, HttpStatus.CREATED);
    }

    @GetMapping("{orgId}")
    public ResponseEntity<Organization> getOrganizationById(@Valid @NonNull @PathVariable Integer orgId) {
        log.info("orgId: {}", orgId);
        Organization organization = organizationService.getOrganizationById(orgId);
        log.info("organization: {}", organization);
        return new ResponseEntity<>(organization, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Organization>> getOrganizations() {
        List<Organization> organizations = organizationService.getOrganizations();
        log.info("organizations: {}", organizations);
        return new ResponseEntity<>(organizations, HttpStatus.OK);
    }

    @PutMapping("{orgId}")
    public ResponseEntity<Organization> updateOrganizationById(@Valid @NonNull @PathVariable Integer orgId,
                                                               @Valid @RequestBody OrganizationDTO organizationDTO) {
        log.info("orgId: {}, organizationDTO: {}", orgId, organizationDTO);
        Organization organization = organizationService.updateOrganizationById(orgId, organizationDTO);
        log.info("organization: {}", organization);
        if (organization != null) {
            return new ResponseEntity<>(organization, HttpStatus.OK);
        }
        log.error(NO_DATA_FOUND.getValue().concat(organization.getOrgId().toString()));
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("{orgId}")
    public ResponseEntity<HttpStatus> deleteOrganizationById(@Valid @NonNull @PathVariable Integer orgId) {
        log.info("orgId: {}", orgId);
        return organizationService.deleteOrganizationById(orgId);
    }

}
