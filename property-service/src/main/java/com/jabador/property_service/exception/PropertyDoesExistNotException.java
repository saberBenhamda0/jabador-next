package com.jabador.property_service.exception;

public class PropertyDoesExistNotException extends RuntimeException {
    public PropertyDoesExistNotException(long id) {
        super("there is no property with the following id " + id + " please use a valid id");
    }
}
