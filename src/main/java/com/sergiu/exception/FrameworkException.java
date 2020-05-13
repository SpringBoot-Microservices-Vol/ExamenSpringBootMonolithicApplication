package com.sergiu.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NO_CONTENT)
public class FrameworkException extends RuntimeException {

    public FrameworkException(String message) {
        super(message);
    }
}
