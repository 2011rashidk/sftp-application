package com.happiest.minds.sftpapplication.controller;

import com.happiest.minds.sftpapplication.dto.EmployeeDTO;
import com.happiest.minds.sftpapplication.response.Employee;
import com.happiest.minds.sftpapplication.service.EmployeeService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.happiest.minds.sftpapplication.enums.Constants.*;


@RestController
@RequestMapping("api/sftp/application/employee")
@Slf4j
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@Valid @RequestBody EmployeeDTO employeeDTO,
                                                   @RequestHeader(name = "Authorization") String jwtToken) {
        log.info(EMPLOYEE_DTO.getValue(), employeeDTO);
        Employee employee = employeeService.createEmployee(employeeDTO, jwtToken);
        log.info(EMPLOYEE.getValue(), employee);
        return new ResponseEntity<>(employee, HttpStatus.CREATED);
    }

    @PostMapping("employees")
    public ResponseEntity<List<Employee>> createEmployees(@Valid @RequestBody List<EmployeeDTO> employeeDTOList,
                                                          @RequestHeader(name = "Authorization") String jwtToken) {
        log.info(EMPLOYEE_DTO.getValue(), employeeDTOList);
        List<Employee> employees = employeeService.createEmployees(employeeDTOList, jwtToken);
        log.info(EMPLOYEE.getValue(), employees);
        return new ResponseEntity<>(employees, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<Employee> getEmployeeById(@Valid @NotEmpty @PathVariable String id,
                                                    @RequestHeader(name = "Authorization") String jwtToken) {
        log.info(ID.getValue(), id);
        Employee employee = employeeService.getEmployeeById(id, jwtToken);
        log.info(EMPLOYEE.getValue(), employee);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getEmployees(@RequestHeader(name = "Authorization") String jwtToken) {
        List<Employee> employees = employeeService.getEmployees(jwtToken);
        log.info(EMPLOYEE.getValue(), employees);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Employee> updateEmployeeById(@Valid @RequestBody EmployeeDTO employeeDTO,
                                                       @RequestHeader(name = "Authorization") String jwtToken) {
        log.info(EMPLOYEE_DTO.getValue(), employeeDTO);
        Employee employee = employeeService.updateEmployeeById(employeeDTO, jwtToken);
        log.info(EMPLOYEE.getValue(), employee);
        if (employee != null) {
            return new ResponseEntity<>(employee, HttpStatus.OK);
        }
        log.error(NO_DATA_FOUND.getValue().concat(employeeDTO.getId()));
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteEmployeeById(@Valid @NotEmpty @PathVariable String id,
                                                         @RequestHeader(name = "Authorization") String jwtToken) {
        log.info(ID.getValue(), id);
        return employeeService.deleteEmployeeById(id, jwtToken);
    }

}
