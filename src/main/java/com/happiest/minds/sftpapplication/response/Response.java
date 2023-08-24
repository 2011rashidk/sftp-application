
package com.happiest.minds.sftpapplication.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class Response {

    private HttpStatus httpStatus;
    private String message;
}
