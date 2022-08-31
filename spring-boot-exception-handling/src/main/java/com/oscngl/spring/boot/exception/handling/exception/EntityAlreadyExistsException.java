package com.oscngl.spring.boot.exception.handling.exception;

public class EntityAlreadyExistsException extends RuntimeException {

    public EntityAlreadyExistsException(String msg) {
        super(msg);
    }

}
