package com.oscngl.spring.boot.kubernetes.exception;

public class EntityAlreadyExistsException extends RuntimeException {

    public EntityAlreadyExistsException(String msg) {
        super(msg);
    }

}
