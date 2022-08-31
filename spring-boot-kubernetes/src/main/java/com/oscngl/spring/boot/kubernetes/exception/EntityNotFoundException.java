package com.oscngl.spring.boot.kubernetes.exception;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(String msg) {
        super(msg);
    }

}
