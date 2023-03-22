package com.project.errors.handler;

import com.project.errors.NoRightsToChangeError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BadCredentialsErrorHandler {

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<String> handleError(BadCredentialsException error){
        return new ResponseEntity<>(error.getMessage(), HttpStatus.UNAUTHORIZED);
    }
}
