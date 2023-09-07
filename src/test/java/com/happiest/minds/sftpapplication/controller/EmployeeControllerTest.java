package com.happiest.minds.sftpapplication.controller;

import com.happiest.minds.sftpapplication.dto.EmployeeDTO;
import com.happiest.minds.sftpapplication.response.Employee;
import com.happiest.minds.sftpapplication.service.EmployeeService;
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

class EmployeeControllerTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    /*@Test
    void testCreateEmployee() {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        Employee employee = new Employee();
        when(employeeService.createEmployee(any(EmployeeDTO.class))).thenReturn(employee);
        ResponseEntity<Employee> response = employeeController.createEmployee(employeeDTO);
        verify(employeeService).createEmployee(any(EmployeeDTO.class));
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(employee, response.getBody());
    }*/

    @Test
    void testCreateEmployees() {
        List<EmployeeDTO> employeeDTOList = new ArrayList<>();

        List<Employee> employees = new ArrayList<>();

        when(employeeService.createEmployees(anyList(), eq("someToken"))).thenReturn(employees);

        ResponseEntity<List<Employee>> response = employeeController.createEmployees(employeeDTOList, "someToken");

        verify(employeeService).createEmployees(anyList(), eq("someToken"));
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(employees, response.getBody());
    }
    @Test
    void testGetEmployeeById() {
        String employeeId = "123";
        Employee employee = new Employee();
        when(employeeService.getEmployeeById(employeeId, "someToken")).thenReturn(employee);
        ResponseEntity<Employee> response = employeeController.getEmployeeById(employeeId, "someToken");
        verify(employeeService).getEmployeeById(employeeId, "someToken");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(employee, response.getBody());
    }

    @Test
    void testGetEmployees() {
        List<Employee> employees = new ArrayList<>();
        when(employeeService.getEmployees("someToken")).thenReturn(employees);
        ResponseEntity<List<Employee>> response = employeeController.getEmployees("someToken");
        verify(employeeService).getEmployees("someToken");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(employees, response.getBody());
    }

    @Test
    void testUpdateEmployeeById() {
        EmployeeDTO employeeDTO = new EmployeeDTO();

        Employee updatedEmployee = new Employee();

        when(employeeService.updateEmployeeById(any(EmployeeDTO.class), eq("someToken"))).thenReturn(updatedEmployee);

        ResponseEntity<Employee> response = employeeController.updateEmployeeById(employeeDTO, "someToken");

        verify(employeeService).updateEmployeeById(any(EmployeeDTO.class), eq("someToken"));
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedEmployee, response.getBody());
    }


}