
package com.happiest.minds.sftpapplication.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {

    private HttpStatus httpStatus;
    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object data;
}
