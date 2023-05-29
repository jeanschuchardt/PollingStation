package com.jb.polling_station.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ApiRequestException extends RuntimeException {
    private String message;
    private HttpStatus status;
    
    public ApiRequestException(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }
}
