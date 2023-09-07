package com.happiest.minds.sftpapplication.service;

import com.happiest.minds.sftpapplication.dto.EmployeeDTO;
import com.happiest.minds.sftpapplication.response.Response;
import com.happiest.minds.sftpapplication.utility.KafkaUtility;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.example.JsonToPojoUtil;
import org.example.SFTPUtil;
import org.example.dto.EmployeeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.happiest.minds.sftpapplication.enums.Constants.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class SftpService {

    @Value("${sftp.username}")
    private String sftpUsername;

    @Value("${sftp.password}")
    private String password;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    KafkaUtility kafkaUtility;

    @Value("${sftp.success.topic}")
    private String successTopic;

    @Value("${sftp.failure.topic}")
    private String failureTopic;

    public ResponseEntity<Response> getListOfFiles(String location) {
        try {
            SFTPUtil sftpUtil = new SFTPUtil();
            List<String> listOfFiles = sftpUtil.getAllFilesInDir(sftpUsername, password, location);
            log.info("List of file: {}", listOfFiles);
            return new ResponseEntity<>(new Response(HttpStatus.OK, LIST_OF_FILES_RETRIEVED.getValue(), listOfFiles), HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(new Response(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Response> readFile(String location, String filename) {
        try {
            SFTPUtil sftpUtil = new SFTPUtil();
            JsonToPojoUtil jsonToPojoUtil = new JsonToPojoUtil();
            String fileContent = sftpUtil.readFileFromSftp(sftpUsername, password, location, filename);
            if (Strings.isEmpty(fileContent)) {
                return new ResponseEntity<>(new Response(HttpStatus.NO_CONTENT, FILE_EMPTY.getValue(), null), HttpStatus.NO_CONTENT);
            }
            log.info("File content: {}", fileContent);
            List<EmployeeModel> employeeDTOS = jsonToPojoUtil.convertJsonToPojoEmployee(fileContent);
            return new ResponseEntity<>(new Response(HttpStatus.OK, FILES_CONTENT_RETRIEVED.getValue(), employeeDTOS), HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(new Response(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<Response> loadFile(String location, String filename, String jwtToken) {
        Response response;
        try {
            SFTPUtil sftpUtil = new SFTPUtil();
            JsonToPojoUtil jsonToPojoUtil = new JsonToPojoUtil();
            String fileContent = sftpUtil.readFileFromSftp(sftpUsername, password, location, filename);
            if (Strings.isEmpty(fileContent)) {
                return new ResponseEntity<>(new Response(HttpStatus.NO_CONTENT, FILE_EMPTY.getValue(), null), HttpStatus.NO_CONTENT);
            }
            log.info("File content: {}", fileContent);
            List<EmployeeModel> employeeDTOS = jsonToPojoUtil.convertJsonToPojoEmployee(fileContent);
            List<EmployeeDTO> employees = employeeDTOS.stream()
                    .map(dto -> new EmployeeDTO(
                            dto.getId(),
                            dto.getName(),
                            dto.getAge(),
                            dto.getEmail(),
                            dto.getMobile(),
                            dto.getLocation(),
                            dto.getOrgId(),
                            dto.getDepartment()))
                    .collect(Collectors.toList());
            employeeService.createEmployees(employees, jwtToken);
            response = new Response(HttpStatus.OK, FILES_CONTENT_SAVED.getValue(), employeeDTOS);
            kafkaUtility.publishToKafka(response, successTopic);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            response = new Response(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null);
            kafkaUtility.publishToKafka(response, failureTopic);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
