package com.susancodes.rest_api_blog_application.exception;


import lombok.Getter;
import lombok.Setter;
@Getter
@Setter

public class ResourceNotFoundException extends RuntimeException{
    private String resourceName;
    private String fieldName;
    private Long fieldvalue;

    public ResourceNotFoundException(String resourceName, String fieldName, Long fieldvalue) {
        super(String.format("%s not found with %s : '%s", resourceName,fieldName,fieldvalue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldvalue = fieldvalue;
    }

}
