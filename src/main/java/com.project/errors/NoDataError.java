package com.project.errors;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public class NoDataError  extends RuntimeException {
    private String message;
    private HttpStatus status;
}
