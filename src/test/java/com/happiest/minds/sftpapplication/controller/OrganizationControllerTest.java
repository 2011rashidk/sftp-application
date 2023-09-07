package com.happiest.minds.sftpapplication.controller;

import com.happiest.minds.sftpapplication.request.OrganizationDTO;
import com.happiest.minds.sftpapplication.response.Organization;
import com.happiest.minds.sftpapplication.service.OrganizationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class OrganizationControllerTest {

    @Mock
    private OrganizationService organizationService;

    @InjectMocks
    private OrganizationController organizationController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateOrganization() {
        OrganizationDTO organizationDTO = new OrganizationDTO();
        Organization organization = new Organization();
        when(organizationService.createOrganization(any(OrganizationDTO.class))).thenReturn(organization);
        ResponseEntity<Organization> response = organizationController.createOrganization(organizationDTO);
        verify(organizationService).createOrganization(any(OrganizationDTO.class));
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(organization, response.getBody());
    }

    @Test
    void testGetOrganizationById() {
        Integer orgId = 1;
        Organization organization = new Organization();
        when(organizationService.getOrganizationById(orgId)).thenReturn(organization);
        ResponseEntity<Organization> response = organizationController.getOrganizationById(orgId);
        verify(organizationService).getOrganizationById(orgId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(organization, response.getBody());
    }
    @Test
    void testGetOrganizations() {
        List<Organization> organizationList = new ArrayList<>();
        when(organizationService.getOrganizations()).thenReturn(organizationList);
        ResponseEntity<List<Organization>> response = organizationController.getOrganizations();
        verify(organizationService).getOrganizations();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(organizationList, response.getBody());
    }
    @Test
    void testUpdateOrganizationById() {
        Integer orgId = 1;
        OrganizationDTO organizationDTO = new OrganizationDTO();

        Organization updatedOrganization = new Organization();

        when(organizationService.updateOrganizationById(eq(orgId), any(OrganizationDTO.class))).thenReturn(updatedOrganization);

        ResponseEntity<Organization> response = organizationController.updateOrganizationById(orgId, organizationDTO);

        verify(organizationService).updateOrganizationById(eq(orgId), any(OrganizationDTO.class)); // Verify service method was called
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedOrganization, response.getBody());
    }

    /*@Test
    void testUpdateOrganizationByIdNotFound() {
        Integer orgId = 1;
        OrganizationDTO organizationDTO = new OrganizationDTO();
        organizationDTO.setName("test");
        OrganizationDTO updatedDto = organizationDTO;
        when(organizationService.updateOrganizationById(orgId, any(OrganizationDTO.class))).thenReturn(null);
        ResponseEntity<Organization> response = organizationController.updateOrganizationById(orgId, updatedDto);
        verify(organizationService).updateOrganizationById(eq(orgId), any(OrganizationDTO.class));
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }*/

    @Test
    void testDeleteOrganizationByIdSuccess() {
        Integer orgId = 1;
        when(organizationService.deleteOrganizationById(orgId)).thenReturn(new ResponseEntity<>(HttpStatus.NO_CONTENT));
        ResponseEntity<HttpStatus> response = organizationController.deleteOrganizationById(orgId);
        verify(organizationService).deleteOrganizationById(orgId);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}