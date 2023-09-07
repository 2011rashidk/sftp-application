/*
package com.happiest.minds.sftpapplication.service;

import com.happiest.minds.sftpapplication.request.OrganizationDTO;
import com.happiest.minds.sftpapplication.response.Organization;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
public class OrganizationServiceTest {

    @Autowired
    private OrganizationService organizationService;

    @Mock
    private MockWebServer mockWebServer;

    @BeforeEach
    public void setUp() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();
    }

    @AfterEach
    public void tearDown() throws IOException {
        mockWebServer.shutdown();
    }

    @Test
    public void testCreateOrganization() {
        String responseBody = "{\"id\": 1, \"name\": \"Test Organization\"}";
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(responseBody));

        OrganizationDTO organizationDTO = new OrganizationDTO();
        organizationDTO.setName("Test Organization");
        Organization organization = organizationService.createOrganization(organizationDTO);

//        assertEquals(1, organization.getId());
        assertEquals("Test Organization", organization.getName());
    }
}*/
