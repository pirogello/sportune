package com.project.errors.handler;

import com.project.errors.NoRightsToChangeError;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class NoDataExceptionsHandler {

    @ExceptionHandler(NoRightsToChangeError.class)
    public ResponseEntity<String> handleError(NoRightsToChangeError error){
        return new ResponseEntity<>(error.getMessage(), error.getStatus());
    }
}
