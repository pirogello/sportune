package com.project.errors;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class NoRightsToChangeError extends RuntimeException {
    private String message;
    private HttpStatus status;
}
