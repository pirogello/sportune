package com.project.errors.handler;

import com.project.errors.NoRightsToChangeError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class NoRightsToChangeErrorHandler {

    @ExceptionHandler(NoRightsToChangeError.class)
    public ResponseEntity<String> handleError(NoRightsToChangeError error){
        return new ResponseEntity<>(error.getMessage(), error.getStatus());
    }
}
