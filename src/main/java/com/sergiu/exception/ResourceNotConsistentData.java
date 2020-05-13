package com.sergiu.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ResourceNotConsistentData extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ResourceNotConsistentData(String resourceName, String fieldName, Object urlValue, Object dtoValue) {
        super(String.format("Wrong request for %s where filedName {%s} on urlValue is %s but in dto is %s", resourceName, fieldName, urlValue, dtoValue));
    }
}
